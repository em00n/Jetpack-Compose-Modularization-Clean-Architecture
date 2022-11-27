package plugins
import dependencies.*
import java.io.FileInputStream
import java.util.*


plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android{
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}


dependencies {
    addFeatureModuleDependantDependencies()
    addHiltDependencies()
    addGsonDependencies()
    addAndroidxCoreDependencies()
    addCoroutinesAndroidDependencies()
    addAndroidResponsiveSizeDependenciesDependencies()
    addAndroidTestsDependencies()
    addAndroidxLifeCycleDependencies()
}