plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.movieappwithmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieappwithmvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
    dataBinding {
       enable = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.databinding.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //project dependecy required
    //for api call retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    //for image loading
    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.squareup.picasso:picasso:2.8")
    //for lottie animation
    implementation("com.airbnb.android:lottie-compose:6.0.1")
    //for dynamic sdp and dp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    //lottie
    implementation("com.airbnb.android:lottie:4.2.0")

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx: 2.2.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx: 2.2.0-alpha01")
    implementation( "androidx.lifecycle:lifecycle-runtime-ktx: 2.2.0-alpha01")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    //paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.0-alpha03")

}