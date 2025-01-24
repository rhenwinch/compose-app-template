plugins {
    alias(libs.plugins.convention.feature)
}

android {
    namespace = "com.example.template.feature.home"
}

dependencies {
    implementation(projects.domain.home)

    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
}
