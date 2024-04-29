plugins {
    id("sri.pokedex.android.library")
    id("sri.pokedex.spotless")
}

android {
    namespace = "com.skydoves.pokedex.compose.core.test"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
}