plugins {
    alias(libs.plugins.at.android.library)
    alias(libs.plugins.at.android.hilt)
}

android {
    namespace = "com.wei.amazingtalker.core.data.test"
}

dependencies {
    api(project(":core:data"))

    implementation(libs.hilt.android.testing)
}