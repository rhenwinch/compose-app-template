package plugin

import ProjectVersions
import com.android.build.api.dsl.ApplicationExtension
import androidApplication
import configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("convention.compose")
                apply("convention.detekt")
                apply("convention.hilt")
            }

            configureKotlinAndroid(commonExtension = extensions.getByType<ApplicationExtension>())

            androidApplication {
                defaultConfig {
                    targetSdk = ProjectVersions.Android.TARGET_SDK

                    applicationId = ProjectVersions.Android.APP_ID
                    versionCode = ProjectVersions.Android.APP_VERSION_CODE
                    versionName = ProjectVersions.Android.APP_VERSION_NAME

                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
            }
        }
    }
}