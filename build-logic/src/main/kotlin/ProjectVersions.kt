import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectVersions {
    object Android {
        const val APP_ID = "com.example.template"

        const val APP_VERSION_MAJOR = 1
        const val APP_VERSION_MINOR = 0
        const val APP_VERSION_PATCH = 0
        const val APP_VERSION_BUILD = 0

        const val APP_VERSION_NAME = "$APP_VERSION_MAJOR.$APP_VERSION_MINOR.$APP_VERSION_PATCH"
        const val APP_VERSION_CODE = APP_VERSION_MAJOR * 10000 + APP_VERSION_MINOR * 1000 + APP_VERSION_PATCH * 100 + APP_VERSION_BUILD

        const val MIN_SDK = 21
        const val COMPILE_SDK = 35
        const val TARGET_SDK = COMPILE_SDK
    }

    object Java {
        val jvmTarget = JvmTarget.JVM_17
        val javaVersion = JavaVersion.VERSION_17
    }
}
