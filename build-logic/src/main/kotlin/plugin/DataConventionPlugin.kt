package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class DataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.hilt")
                apply("convention.testing")
            }
        }
    }
}
