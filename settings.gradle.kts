@file:Suppress("UnstableApiUsage", "DEPRECATION")

include(":app")

include(":assets")


include(":common")


include(":feature:profile")


include(":feature:repositories")


include(":feature")


include(":model:entity")


include(":model:api-response")

include(":domain")


include(":di")


include(":data")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    gradle.projectsLoaded {
        plugins {
            plugins {
                id("com.android.application") version (extra.properties["androidGradlePluginVersion"].toString())
                id("com.android.library") version (extra.properties["androidGradlePluginVersion"].toString())
                id("org.jetbrains.kotlin.android") version (extra.properties["kotlinVersion"].toString())
                id("com.google.dagger.hilt.android") version (extra.properties["hiltVersion"].toString())

            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Jetpack Compose Multimodule App"

