plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.wei.amazingtalker_recruit.core.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))

    // PublicLibs
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.tracing.ktx)
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit2
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
}