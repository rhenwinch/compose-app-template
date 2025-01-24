package tool

import detektPlugins
import libs
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }

            dependencies {
                detektPlugins(libs.findLibrary("detekt.twitter.compose.rules"))
                detektPlugins(libs.findLibrary("detekt.formatting"))
            }

            tasks.withType<Detekt> {
                parallel = true
                config.setFrom(files("${rootProject.rootDir}/config/detekt/detekt.yml"))
                include("**/*.kt")
                include("**/*.kts")
                exclude("**/resources/**")
                exclude("**/build/**")
                reports {
                    txt {
                        required.set(true)
                    }
                    sarif {
                        required.set(false)
                    }
                    xml {
                        required.set(false)
                    }
                    md {
                        required.set(false)
                    }
                    html {
                        required.set(false)
                    }
                }
            }
        }
    }
}