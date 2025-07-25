import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.yavshok.app"
    compileSdk = 34

    // Load properties from local.properties file
    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    if (localPropertiesFile.exists()) {
        localProperties.load(FileInputStream(localPropertiesFile))
    }

    defaultConfig {
        applicationId = "ru.yavshok.app"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        vectorDrawables {
            useSupportLibrary = true
        }
        
        // Add build config fields

        buildConfigField("String", "BASE_URL", "\"${project.findProperty("API_BASE_URL")}\"")
    }

    signingConfigs {
        create("release") {
            // Only configure release signing if all required properties are present
            val keyAlias = localProperties.getProperty("KEY_ALIAS")
            val keyPassword = localProperties.getProperty("KEY_PASSWORD")
            val keystorePath = localProperties.getProperty("KEYSTORE_PATH")
            val keystorePassword = localProperties.getProperty("KEYSTORE_PASSWORD")
            
            if (keyAlias != null && keyPassword != null && keystorePath != null && keystorePassword != null) {
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
                this.storeFile = rootProject.file(keystorePath)
                this.storePassword = keystorePassword
            }
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            // Debug builds will use the default debug keystore automatically
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Only apply release signing config if keystore properties are available
            val hasKeystoreConfig = localProperties.getProperty("KEY_ALIAS") != null &&
                    localProperties.getProperty("KEY_PASSWORD") != null &&
                    localProperties.getProperty("KEYSTORE_PATH") != null &&
                    localProperties.getProperty("KEYSTORE_PASSWORD") != null
            
            if (hasKeystoreConfig) {
                signingConfig = signingConfigs.getByName("release")
            }
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
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    
    // Downgraded to versions compatible with compileSdk 33
    implementation("androidx.activity:activity-compose:1.7.2")
    
    // Use a compatible Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    
    // Navigation - compatible with compileSdk 33
    implementation("androidx.navigation:navigation-compose:2.5.3")
    
    // ViewModel - compatible with compileSdk 33
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    
    // HTTP client
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // App Metrica

    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    
    // Konfetti for confetti animation
    implementation("nl.dionsegijn:konfetti-compose:2.0.5")
    
    // Coil for GIF loading
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.github.javafaker:javafaker:1.0.2")

    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation("com.google.code.gson:gson:2.10.1")
}