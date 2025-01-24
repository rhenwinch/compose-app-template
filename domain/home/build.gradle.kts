plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.example.template.domain.home"
}

dependencies {
    implementation(projects.model.home)
}
