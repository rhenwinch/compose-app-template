import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import java.util.Optional
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private typealias Dependency = Optional<Provider<MinimalExternalModuleDependency>>

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}

internal fun DependencyHandlerScope.ksp(dependency: Dependency) {
    add("ksp", dependency.get())
}

internal fun DependencyHandlerScope.androidTestImplementation(dependency: Dependency) {
    add("androidTestImplementation", dependency.get())
}

internal fun DependencyHandlerScope.testImplementation(dependency: Dependency) {
    add("testImplementation", dependency.get())
}

internal fun DependencyHandlerScope.implementation(dependency: Dependency) {
    add("implementation", dependency.get())
}

internal fun DependencyHandlerScope.detektPlugins(dependency: Dependency) {
    add("detektPlugins", dependency.get())
}

internal fun DependencyHandlerScope.coreLibraryDesugaring(dependency: Dependency) {
    add("coreLibraryDesugaring", dependency.get())
}

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = ProjectVersions.Android.COMPILE_SDK

        if (commonExtension is LibraryExtension) {
            testOptions.targetSdk = ProjectVersions.Android.TARGET_SDK
        }

        defaultConfig {
            minSdk = ProjectVersions.Android.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                merges += "META-INF/LICENSE.md"
                merges += "META-INF/LICENSE-notice.md"
            }
        }

        compileOptions {
            sourceCompatibility = ProjectVersions.Java.javaVersion
            targetCompatibility = ProjectVersions.Java.javaVersion
            isCoreLibraryDesugaringEnabled = true
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(ProjectVersions.Java.jvmTarget)

            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())

            freeCompilerArgs.set(
                freeCompilerArgs.get() + listOf(
                    // Enable experimental coroutines APIs, including Flow
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                )
            )
        }
    }

    dependencies {
        coreLibraryDesugaring(libs.findLibrary("desugar"))
    }
}
