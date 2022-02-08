plugins {
    id("com.android.application") version "7.0.4" apply false
    id("com.android.library") version "7.0.4" apply false

    val kotlinVersion = "1.6.10"
    kotlin("android") version kotlinVersion apply false
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

group = "app.saboten"
version = "1.0.00"