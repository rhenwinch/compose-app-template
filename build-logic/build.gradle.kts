import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    compileOnly(libs.gradlePlugin.android)
    compileOnly(libs.gradlePlugin.kotlin)
    compileOnly(libs.gradlePlugin.ksp)
    implementation(libs.gradlePlugin.detekt)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {
        register("android.app") {
            id = "convention.android.application"
            implementationClass = "plugin.AndroidApplicationConventionPlugin"
        }
        register("android.lib") {
            id = "convention.android.library"
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = "convention.compose"
            implementationClass = "plugin.ComposeConventionPlugin"
        }
        register("hilt") {
            id = "convention.hilt"
            implementationClass = "plugin.HiltConventionPlugin"
        }
        register("feature") {
            id = "convention.feature"
            implementationClass = "plugin.FeatureConventionPlugin"
        }
        register("data") {
            id = "convention.data"
            implementationClass = "plugin.DataConventionPlugin"
        }
        register("testing") {
            id = "convention.testing"
            implementationClass = "plugin.TestingConventionPlugin"
        }

        // == TOOLS
        register("detekt") {
            id = "convention.detekt"
            implementationClass = "tool.DetektPlugin"
        }
    }
}
