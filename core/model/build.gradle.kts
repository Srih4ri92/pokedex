plugins {
    id("sri.pokedex.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("sri.pokedex.spotless")
}

android{
    namespace = "com.sri.pokedex.core.model"
}

dependencies{
    // compose stable marker
    compileOnly(libs.compose.stable.marker)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // kotlinx
    api(libs.kotlinx.immutable.collection)
}