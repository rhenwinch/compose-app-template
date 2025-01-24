plugins {
    alias(libs.plugins.convention.data)
}

android {
    namespace = "com.example.template.data.home"
}

dependencies {
    implementation(projects.domain.home)
}