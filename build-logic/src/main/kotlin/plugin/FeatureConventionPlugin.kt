package plugin

import implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.compose")
                apply("convention.hilt")
            }

            dependencies {
                implementation(project(":core:theme"))

                implementation(libs.findLibrary("hilt.navigation"))
                implementation(libs.findLibrary("lifecycle.compose.runtime"))
                implementation(libs.findLibrary("lifecycle.compose.viewmodel"))
            }
        }
    }
}
