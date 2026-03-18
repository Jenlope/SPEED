//import java.util.Properties
//import org.gradle.internal.impldep.junit.runner.Version.id
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

/*
val apikeyProperties = Properties()
val apikeyPropertiesFile = rootProject.file("apikeys.properties")

if (apikeyPropertiesFile.exists()) {
    apikeyProperties.load(apikeyPropertiesFile.inputStream())
}

 */

android {
    namespace = "com.example.speed"
    compileSdk {
        version = release(36)
    }

    buildFeatures {
        buildConfig = true
    }


    defaultConfig {
        applicationId = "com.example.speed"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        defaultConfig {
            /*
            buildConfigField(
                "String",
                "SPEEDAPP_APIKEY",
                "\"${project.findProperty("SPEEDAPP_APIKEY")}\""
            )
            manifestPlaceholders["SPEEDAPP_APIKEY"] =
                apikeyProperties["SPEEDAPP_APIKEY"] ?: ""

             */
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.mapbox.maps:android:11.0.0")
    implementation("com.mapbox.navigationcore:android:3.20.0")
    implementation("com.mapbox.navigationcore:ui-maps:3.20.0")

    //implementation("com.mapbox.navigationcore:android-ndk27:3.20.0")
    //implementation("com.mapbox.navigationcore:ui-maps-ndk27:3.20.0")
}