pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
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

rootProject.name = "effective mobile"
include(":app")
include(":data")
include(":feature:tickets")
include(":feature:hotels")
include(":feature:shorter")
include(":feature:subscriptions")
include(":feature:profile")
include(":core:ui")
include(":core:utils")
