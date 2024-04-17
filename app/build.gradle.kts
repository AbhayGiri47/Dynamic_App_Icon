plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

private val mainActivity = "com.abhay.dynamicappicon.MainActivity"
private val mainActivityAlias = "com.abhay.dynamicappicon.MainActivityAlias"

android {
    namespace = "com.abhay.dynamicappicon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abhay.dynamicappicon"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders.apply {
            set("main_activity", mainActivity)
            set("main_activity_alias", mainActivityAlias)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "main_activity", "\"${mainActivity}\"")
            buildConfigField("String", "main_activity_alias", "\"${mainActivityAlias}\"")
        }

        debug {
            isDebuggable = true

            buildConfigField("String", "main_activity", "\"${mainActivity}\"")
            buildConfigField("String", "main_activity_alias", "\"${mainActivityAlias}\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}