plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose)
}

android {
    namespace = "com.example.template.core.theme"

    lint {
        warningsAsErrors = true
        abortOnError = true
        disable.add("GradleDependency")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.text.google.fonts)
    implementation(libs.material)
}
