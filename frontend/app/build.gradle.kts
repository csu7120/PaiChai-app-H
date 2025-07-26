plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.paichai.healthhelper"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.paichai.healthhelper"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.inappmessaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("androidx.annotation:annotation:1.7.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //이미지 비동기 로드용 Glide 라이브러리
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Glide에서 어노테이션 기반 코드 자동 생성 (ViewHolder에 사용 가능)
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
}