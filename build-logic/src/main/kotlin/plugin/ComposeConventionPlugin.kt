package plugin

import com.android.build.gradle.BaseExtension
import androidTestImplementation
import implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<BaseExtension> {
                buildFeatures.apply {
                    compose = true
                    viewBinding = true
                }

                dependencies {
                    val bom = libs.findLibrary("compose.bom").get()
                    add("implementation", platform(bom))
                    add("androidTestImplementation", platform(bom))
                }
            }
        }
    }
}
