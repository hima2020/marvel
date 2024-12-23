plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "org.rawafedtech.marvelapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.rawafedtech.marvelapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "public_key", "\"082f6426bdd8e2645731fbb226271bd6\"")
        buildConfigField("String", "private_key", "\"99f753cb33c0a32fd95e4cd9e7fce40b8a243d59\"")
        buildConfigField("String", "base_url", "\"https://gateway.marvel.com/v1/public/\"")

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
        buildConfig = true
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

    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.foundation)
    //compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.android)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.google.android.material)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.room.ktx)

    // animations

    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core)
    implementation(libs.lottie.compose)
    implementation(libs.shimmer.compose)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestUtil(libs.androidx.test.orchestrator)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.animation.core)

    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)


    // moshi
    implementation(libs.converter.moshi)
    //noinspection KaptUsageInsteadOfKsp
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.adapter.rxjava3)


    // hashing
    implementation(libs.xercesimpl)
    implementation(libs.jaxb.api)


// Assertions
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.truth)
    androidTestImplementation(libs.google.test.truth)

// Espresso dependencies
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.test.espresso.contrib)
    androidTestImplementation(libs.test.espresso.intents)
    androidTestImplementation(libs.test.espresso.accessibility)
    androidTestImplementation(libs.test.idling.concurrent)

    implementation(libs.mockito.kotlin)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.test)
    //Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.hilt.android.compiler)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.encrypted.preferences)
    //Glide
    implementation(libs.landscapist.glide)

    implementation(libs.androidx.navigation.compose)
    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    ksp(libs.androidx.room.compiler)
    implementation(kotlin("test"))
}