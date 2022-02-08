pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
        maven(url = "https://repo.repsy.io/mvn/chrynan/public")
    }
}

rootProject.name = "android-compose-simple-mvvm-uda-example"
include(":app")
