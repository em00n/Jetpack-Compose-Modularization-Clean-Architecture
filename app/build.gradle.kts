import dependencies.*
import java.text.SimpleDateFormat
import java.util.*

plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace ="com.emon.jetpackcomposemultimoduleapp"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testRunner
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        applicationVariants.all {
            val variant = this
            variant.outputs
                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    val currentDateTime = Calendar.getInstance().time
                    val dateFormat = SimpleDateFormat("dd-MM-yy-hh-mm-a")
                    val date = dateFormat.format(currentDateTime)
                    val outputFileName = "Multimodule App" + "_" +  defaultConfig.versionName + "_" + defaultConfig.versionCode + "_" + date + "_" + variant.name + ".apk"
                    output.outputFileName = outputFileName
                }
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    addHiltDependencies()
    addAndroidxCoreDependencies()
    addAndroidxLifeCycleDependencies()

    addDataModule()
    addDomainModule()
    addRepositoriesModule()
    addProfileModule()
    addRxjava3Dependencies()
    addRoomDependencies()
//    addPlayStoreAppUpdateDependencies()
//    addFirebaseDependencies()
//    addFacebookDependencies()
//    addLeakcanaryAndComposeDependencies()
//    addAndroidTestsDependencies()
    //addGoogleMapDependencies()
    //addPicassoDependencies()
}
kapt {
    correctErrorTypes = true
}