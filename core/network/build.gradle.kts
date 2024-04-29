plugins{
    id("sri.pokedex.android.library")
    id("sri.pokedex.android.hilt")
    id("sri.pokedex.spotless")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android{
    namespace= "com.sri.pokedex.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies{
    implementation(projects.core.model)
    implementation(projects.core.test)

    // coroutines
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.sandwich)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.androidx.arch.core)

    // json parsing
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}