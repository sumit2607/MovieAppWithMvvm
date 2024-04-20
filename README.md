# VideoCallApp

Welcome to **My Video Call App**! This repository hosts the source code for an amazing Android app that.


###  
App Name                   | Describe                  | Tech Stack             | Google Play 
:------------------------: | :------------------------ | :------------------------: | :------------------------: 
Movie Data | Movie Stream is an application where one can share and watch short videos,. | Android, Kotlin, Glide, RoomDb Mvvm, Material UI, Navigation Component | [![Get it on Google Play](https://firebasestorage.googleapis.com/v0/b/snapchat-f2264.appspot.com/o/T9HnFlW.png?alt=media&token=b46055e4-3b02-424f-9e88-862543831a8b)](https://play.google.com/store/apps/details?id=com.angel.snapchat)

## Features

- Conncet People with Live Stream and Video call
- Login with google securly
- Voice calling
- Video feed like tik tok

## Getting Started


## Instructions to clone this project ✌
1. Open Android Studio.
2. Go to File > New > Project From Version Control.
3. Copy the link of this repositary.
4. Paste the link in Url Box of Android Studio window and click on "Clone" button..

## Dependencies

  //navigation
    def nav_version = "2.3.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

    //Retrofit
    def retrofit2_version = "2.9.0"
    def okhttp3_version = "4.9.0"
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp3_version"

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'

    //paging
    def paging_version = "3.1.0-alpha03"
    implementation "androidx.paging:paging-runtime-ktx:$paging_version"

    //shimmer
    implementation 'com.facebook.shimmer:shimmer:0.1.0@aar'

    //lottie
    implementation 'com.airbnb.android:lottie:4.2.0'
    implementation 'com.intuit.sdp:sdp-android:1.1.1'
    //room db
    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.room:room-ktx:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")    ## Here are the some screenshots of the cloned application

![GitHub Cards Preview](https://github.com/sumit2607/MovieAppWithMvvm/blob/master/my.png)
![GitHub Cards Preview](https://github.com/sumit2607/MovieAppWithMvvm/blob/master/vid.mp4)

## Contributing

Contributions are welcome! If you find any issues or want to enhance the app, feel free to submit a pull request.

## License

This project is free to use.

## Contact

Have questions or suggestions? Feel free to reach out to Sumit or harnoor via linkdin.
