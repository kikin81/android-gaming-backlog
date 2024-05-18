plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.sortDependencies)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.secrets)
    alias(libs.plugins.ktlint)
}

secrets {
    defaultPropertiesFileName = "local.defaults.properties"
}

android {
    namespace = "us.kikin.android.gamingbacklog"
    compileSdk = 34

    defaultConfig {
        applicationId = "us.kikin.android.gamingbacklog"
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
                "proguard-rules.pro",
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
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    ksp(libs.hilt.compiler)
    ksp(libs.moshi.codegen)

    ktlintRuleset(libs.ktlint.compose.rules)

    lintChecks(libs.slack.compose.lint)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.accompanist.placeholder)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    implementation(libs.igdb.api)
    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)

    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
