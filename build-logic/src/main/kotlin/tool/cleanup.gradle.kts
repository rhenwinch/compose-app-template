/**
 * A plugin to cleanup the template after it has been forked. It register a single `templateCleanup`
 * task that is designed to run from CI. It:
 * - renames the root project
 * - replaces the maven coordinates with coordinates based on the Github repository where the
 * template is forked
 * - changes the package name
 * - changes the Android application ID
 * - cleanups after itself by removing the Github action and this plugin
 */

abstract class CleanupTask : DefaultTask() {
    @TaskAction
    fun action() {
        with(project) {
            check(rootProject.name == name) {
                "The cleanup plugin should be applied to the root project and not $name"
            }

            val repository =
                System.getenv("GITHUB_REPOSITORY")
                    ?: error("No GITHUB_REPOSITORY environment variable. Are you running from Github Actions?")

            val (owner, name) = repository.split("/")
            val sanitizedOwner = owner.sanitized()
            val sanitizedName = name.sanitized()

            file("settings.gradle.kts").replace(
                "rootProject.name = (\"compose-app-template\")",
                "rootProject.name = (\"$name\")",
            )
            file("build-logic/src/main/kotlin/ProjectVersions.kt").replace(
                "com.example.template",
                "com.$sanitizedOwner.$sanitizedName",
            )
            file("app/src/main/res/values/strings.xml").replace(
                "<string name=\"app_name\">Compose App</string>",
                "<string name=\"app_name\">$name</string>",
            )

            patchReadme(repository, name)
            changePackageName(sanitizedOwner, sanitizedName)

            // cleanup the cleanup :)
            file(".github/template-cleanup").deleteRecursively()
            file(".github/workflows/cleanup.yaml").delete()

            val cleanupScript = "build-logic/src/main/kotlin/tool/cleanup.gradle.kts"
            file(cleanupScript).delete()
            file("build.gradle.kts").replace(
                "\n\nval cleanupScript = \"$cleanupScript\"\n" +
                        "if (File(rootDir, cleanupScript).exists()) apply(cleanupScript)",
                "",
            )
        }
    }

    fun String.sanitized() = replace(Regex("[^A-Za-z0-9]"), "").lowercase()

    fun File.replace(
        regex: Regex,
        replacement: String,
    ) {
        writeText(readText().replace(regex, replacement))
    }

    fun File.replace(
        oldValue: String,
        newValue: String,
    ) {
        writeText(readText().replace(oldValue, newValue))
    }

    fun Project.patchReadme(
        repository: String,
        name: String,
    ) {
        val newIntro =
            file(".github/template-cleanup/README.md")
                .readText()
                .replace("%NAME%", name)
                .replace("%REPOSITORY%", repository)

        var featuresFound = false
        val existingReadme =
            file("README.md")
                .readLines()
                .mapNotNull {
                    if (it.startsWith("## Features")) {
                        featuresFound = true
                    }
                    if (!featuresFound) null else it
                }.joinToString("\n")

        file("README.md").writeText(newIntro + "\n" + existingReadme)
    }

    fun Project.srcDirectories() =
        projectDir
            .listFiles()!!
            .filter { it.isDirectory && !(it.name == "build-logic") }
            .flatMap { dir ->
                dir.walk()
                    .filter { it.isDirectory && it.name == "src" }
                    .toList()
            }

    fun Project.changePackageName(
        owner: String,
        name: String,
    ) {
        srcDirectories().forEach {
            it
                .walk()
                .filter {
                    it.isFile && (it.extension == "kt" || it.extension == "kts" || it.extension == "xml")
                }.forEach {
                     it.replace("com.example.template", "com.$owner.$name")
                }
        }
        srcDirectories().forEach {
            it
                .listFiles()!!
                .filter { it.isDirectory } // down to src/main
                .flatMap { it.listFiles()!!.filter { it.isDirectory } } // down to src/main/java
                .forEach { basePackageDir ->
                    val oldPackageRoot = File(basePackageDir, "com/example/template")
                    val newPackageRoot = File(basePackageDir, "com/$owner/$name")

                    if (oldPackageRoot.exists()) {
                        newPackageRoot.mkdirs()
                        oldPackageRoot.copyRecursively(newPackageRoot, overwrite = true)
                        oldPackageRoot.deleteRecursively()
                        File(basePackageDir, "com/example").deleteRecursively()
                    }
                }
        }
    }
}

tasks.register<CleanupTask>("templateCleanup")
