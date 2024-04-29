plugins {
    id("sri.pokedex.android.library")
    id("sri.pokedex.spotless")
}
android {
    namespace = "com.sri.pokedex.core.viewmodel"
}

dependencies {
    api(libs.androidx.lifecycle.viewModelCompose)
}