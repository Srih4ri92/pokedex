pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("androidx\\..*")
                includeGroupByRegex("com\\.android(\\..*|)")
                includeGroupByRegex("com\\.google\\.android\\..*")
                includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
                includeGroupByRegex("com\\.google\\.gms(\\..*|)")
                includeGroupByRegex("com\\.google\\.mlkit")
                includeGroupByRegex("com\\.google\\.oboe")
                includeGroupByRegex("com\\.google\\.prefab")
                includeGroupByRegex("com\\.google\\.testing\\.platform")
            }
            mavenContent {
                releasesOnly()
            }
        }
        mavenCentral() {
            mavenContent {
                releasesOnly()
            }
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pokedex"
include(":app")
include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:test")
include(":core:database")
include(":core:data")
include(":core:viewmodel")
include(":core:preview")
include(":feature:home")
include(":feature:detail")
