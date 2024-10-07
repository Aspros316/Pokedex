plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias (libs.plugins.hilt.application)
    alias(libs.plugins.apollo)
    kotlin("kapt")
}

android {
    namespace = "com.example.pokedex"
    compileSdk = 34

    apollo {
        service("service") {
            packageName.set("com.example.rocketreserver")
            introspection { // highlight-line
                endpointUrl.set("https://graphql-pokeapi.graphcdn.app/") // highlight-line
                schemaFile.set(file("src/main/graphql/schema.sdl")) // highlight-line
            } // highlight-line
        }
    }

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.material.icons.extended)

    //datastore
    implementation (libs.datastore)
    implementation (libs.datastore.core)

    //hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    implementation (libs.hilt.navigation.compose)

    //retrofit
    implementation (libs.retrofit)
    implementation(libs.retrofit.gson)

    //paging3
    implementation (libs.paging.runtime)
    implementation (libs.paging.compose)

    //coil
    implementation (libs.coil)

    //compose animation
    implementation(libs.navigation.compose)

    // room
    implementation(libs.room.ktx)
    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.room.compiler)

    // apollo graphgql
    implementation(libs.apollo.runtime)

    // OkHttp
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}