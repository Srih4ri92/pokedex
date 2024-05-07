plugins {
    id("sri.pokedex.android.library")
    id("sri.pokedex.android.library.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("sri.pokedex.android.hilt")
    id("sri.pokedex.spotless")
}

android {
    namespace = "com.sri.pokedex.core.navigation"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.animation)
    api(libs.androidx.navigation.compose)

    implementation(libs.moshi)
}