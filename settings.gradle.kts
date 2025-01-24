pluginManagement {
    includeBuild("build-logic") // WARN: Don't remove! This one's important.
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal() // <- For locally published testing
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = ("compose-app-template")

include(":app")

// == CORE MODULES
include(":core:theme")

// == FEATURE/UI/PRESENTATION LAYER
include(":feature:home")

// == DATA LAYER
include(":data:home")

// == DOMAIN LAYER
include(":domain:home")

// == MODELS/ENTITIES
include(":model:home")
