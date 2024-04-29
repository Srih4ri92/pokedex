plugins {
    id("sri.pokedex.android.library")
    id("sri.pokedex.android.library.compose")
    id("sri.pokedex.android.hilt")
    id("sri.pokedex.spotless")
}

android {
    namespace = "com.skydoves.pokedex.compose.feature.preview"
}

dependencies {
    // core
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
}