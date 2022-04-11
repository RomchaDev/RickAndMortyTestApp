import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.romeo.rickandmortytestapp"
    const val compileSdk = 31
    const val minSdk = 26
    const val targetSdk = 30
    const val kotlinVersion = "1.5.30"
    const val buildTools = "30.0.3"
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    //Tools
    const val multidex = "2.0.1"

    //Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.30"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "3.12.1"
    const val flowAdapter = "1.0.0"

    //Glide
    const val glide = "4.9.0"
    const val glideCompiler = "4.9.0"

    //Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"
    const val ext = "1.1.3"

    //DI
    const val koin = "2.2.3"

    //Navigation
    const val navigation = "2.4.1"

    //DataBinding
    const val dataBindingCompiler = "3.2.0-alpha10"

    //DataStore
    const val dataStore = "1.0.0"

    //Fragment ktx
    const val fragmentKtx = "1.4.1"
}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val flowAdapter =
        "tech.thdev:flow-call-adapter-factory:${Versions.flowAdapter}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinViewModel = "io.insert-koin:koin-android-viewmodel:${Versions.koin}"
}

object DataBinding {
    const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.dataBindingCompiler}"
}

object Navigation {
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object DataStore {
    const val dataStore = "androidx.datastore:datastore-core:${Versions.dataStore}"
    const val preferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
}

object FragmentKtx {
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val character = ":character"
    const val main = ":main"
    const val signUpLogIn = ":sign-up-log-in"
    const val utils = ":utils"
    const val coreUi = ":core:ui"
}