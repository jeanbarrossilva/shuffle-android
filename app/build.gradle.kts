import com.jeanbarrossilva.shuffle.Versions

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
    id("kotlin-android")
}

android {
    namespace = "com.jeanbarrossilva.shuffle.app"
    compileSdk = Versions.Tick.TARGET_SDK

    defaultConfig {
        applicationId = "com.jeanbarrossilva.shuffle"
        minSdk = Versions.Tick.MIN_SDK
        targetSdk = Versions.Tick.TARGET_SDK
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.room:room-ktx:${Versions.ROOM}")
    implementation("androidx.room:room-runtime:${Versions.ROOM}")

    @Suppress("SpellCheckingInspection")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.31.2-alpha")
    implementation("com.jeanbarrossilva.loadable:loadable:1.4.1")
    implementation("io.coil-kt:coil:2.3.0")

    @Suppress("SpellCheckingInspection")
    implementation(
        "io.github.raamcosta.compose-destinations:animations-core:${Versions.COMPOSE_DESTINATIONS}"
    )

    @Suppress("SpellCheckingInspection")
    implementation("io.github.raamcosta.compose-destinations:core:${Versions.COMPOSE_DESTINATIONS}")

    implementation("io.insert-koin:koin-androidx-compose:3.4.4")

    @Suppress("SpellCheckingInspection")
    implementation("me.saket.swipe:swipe:1.1.1")

    ksp("androidx.room:room-compiler:${Versions.ROOM}")

    @Suppress("SpellCheckingInspection")
    ksp("io.github.raamcosta.compose-destinations:ksp:${Versions.COMPOSE_DESTINATIONS}")
}
