import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")




}

android {
    namespace = "com.example.financeapplication"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.financeapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Replace com.example.android.dagger with your class path.
        testInstrumentationRunner =  "com.example.financeapplication.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        //return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_FINANCE") ?: ""
        val apiToken = properties.getProperty("TOKEN") ?: ""
        buildConfigField(
            type = "String",
            name = "API_FINANCE",
            value = apiKey
        )
        buildConfigField(
            type = "String",
            name = "TOKEN",
            value = apiToken
        )
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
        viewBinding = true
        buildConfig = true


    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"

        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.7.0")


    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    //implementation ("com.google.dagger:hilt-android-compiler:2.45")

    kapt ("com.google.dagger:hilt-android-compiler:2.50")
    //kapt ("androidx.hilt:hilt-compiler:1.2.0")


    // unit and ui testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    testImplementation ("org.mockito:mockito-core:2.28.2")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("org.mockito:mockito-android:2.24.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.0")
    testImplementation ("org.mockito:mockito-inline:2.13.0")

    //espresso
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")

    debugImplementation ("androidx.test:monitor:1.7.2")
    debugImplementation("androidx.test:core:1.6.1")
    debugImplementation("androidx.test:rules:1.6.1")
    debugImplementation("androidx.test:runner:1.6.2")


    debugImplementation("androidx.fragment:fragment-testing-manifest:1.8.2")
        //debugImplementation("androidx.fragment:fragment-testing:1.8.2")
    debugImplementation ("androidx.fragment:fragment-testing:$1.2.0"){
        exclude (group= "androidx.test", module= "core")
    }



    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // retrofit dependency
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Dependency Injection Dagger
//    implementation("com.google.dagger:dagger:2.51.1")
//    implementation("com.google.dagger:dagger-compiler:2.51.1")


    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")



    // Kotlin
    debugImplementation( "androidx.fragment:fragment-ktx:1.8.2")
    // Compose
    implementation( "androidx.fragment:fragment-compose:1.8.2")

    //implementation ("android.arch.navigation:navigation-fragment-ktx:1.0.0")

    // Testing Fragments in Isolation
    //debugImplementation( "androidx.fragment:fragment-testing-manifest:1.8.2")
    //androidTestImplementation ( "androidx.fragment:fragment-testing:1.8.2")

    //hilt testing
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.44")

    androidTestAnnotationProcessor ("com.google.dagger:hilt-android-compiler:2.44")
    testImplementation ("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kaptTest ("com.google.dagger:hilt-android-compiler:2.44")
    // ...with Java.
    testAnnotationProcessor ("com.google.dagger:hilt-android-compiler:2.44")


}

hilt {
    enableAggregatingTask = true
}