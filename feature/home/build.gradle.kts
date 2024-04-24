plugins {
    id("sri.pokedex.android.library")
    id("sri.pokedex.android.library.compose")
    id("sri.pokedex.android.hilt")
    id("sri.pokedex.spotless")
}

android {
    namespace = "com.sri.pokedex.compose.feature.home"
}

dependencies {
    // core
    implementation(projects.core.designsystem)
    
}