import org.gradle.kotlin.dsl.`kotlin-dsl`
plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
  /*  gradlePluginPortal()*/
    google()
    maven ("https://jitpack.io")
    maven ("https://oss.jfrog.org/libs-snapshot")
}
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven ("https://jitpack.io")
        maven ("https://oss.jfrog.org/libs-snapshot")
    }
    extra.apply {
        set("kotlinVersion",project.properties["kotlinVersion"])
        set("hiltVersion",System.getProperty("hiltVersion"))
        set("navigationVersion",System.getProperty("navigationVersion"))
        set("androidGradlePluginVersion",project.properties["androidGradlePluginVersion"])
    }
}
dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.properties["kotlinVersion"]}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${System.getProperty("hiltVersion")}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${System.getProperty("navigationVersion")}")
}