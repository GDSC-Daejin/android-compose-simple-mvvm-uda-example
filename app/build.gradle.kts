import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(AndroidX.activity.ktx)
    implementation(Google.android.material)
    implementation(AndroidX.paging.compose)
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.activity.compose)

    implementation(AndroidX.compose.compiler)
    implementation(AndroidX.compose.animation)
    implementation(AndroidX.compose.material.icons.core)
    implementation(AndroidX.compose.material.icons.extended)
    implementation(AndroidX.compose.foundation)
    implementation(AndroidX.compose.runtime)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)

    implementation(AndroidX.constraintLayout.compose)

    implementation(COIL.compose)
    implementation(COIL.composeBase)

    implementation(Google.accompanist.insets)
    implementation(Google.accompanist.insets.ui)
    implementation(Google.accompanist.pager)
    implementation(Google.accompanist.pager.indicators)
    implementation(Google.accompanist.systemuicontroller)
    implementation(Google.accompanist.swiperefresh)
    implementation(Google.accompanist.flowlayout)

    implementation(AndroidX.core.splashscreen)
    implementation(JakeWharton.timber)

    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)
    implementation(AndroidX.hilt.navigationCompose)
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "app.harry.udaexample"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",

            "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",

            "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",

            "-Xopt-in=coil.annotation.ExperimentalCoilApi",

            "-Xopt-in=com.russhwolf.settings.ExperimentalSettingsApi",
            "-Xopt-in=com.russhwolf.settings.ExperimentalSettingsImplementation",
        )
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.ui)
    }

}