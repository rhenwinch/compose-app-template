package plugin

import libs
import testImplementation
import androidTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                testImplementation(libs.findLibrary("mockk"))
                testImplementation(libs.findLibrary("coroutines.test"))
                testImplementation(libs.findLibrary("junit"))

                androidTestImplementation(libs.findLibrary("mockk"))
                androidTestImplementation(libs.findLibrary("coroutines.test"))
                androidTestImplementation(libs.findLibrary("androidx.test.ext.junit"))
                androidTestImplementation(libs.findLibrary("androidx.test.ext.junit.ktx"))
                androidTestImplementation(libs.findLibrary("androidx.test.rules"))
                androidTestImplementation(libs.findLibrary("androidx.test.runner"))
                androidTestImplementation(libs.findLibrary("espresso.core"))
            }

        }
    }

}