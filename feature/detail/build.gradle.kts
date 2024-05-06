plugins{
    id("sri.pokedex.android.library")
    id("sri.pokedex.android.library.compose")
    id("sri.pokedex.android.hilt")
    id("sri.pokedex.spotless")
}

android {
    namespace = "com.sri.pokedex.compose.feature.details"
}

dependencies {
    // core
    implementation(projects.core.designsystem)
    implementation(projects.core.viewmodel)
    implementation(projects.core.data)
    compileOnly(projects.core.preview)
}
