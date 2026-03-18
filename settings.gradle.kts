import java.util.Properties
import java.io.FileInputStream
val mapboxProperties = Properties()
val mapboxPropertiesFile = File(rootDir, "mapbox.properties")

if (mapboxPropertiesFile.exists()) {
    mapboxProperties.load(FileInputStream(mapboxPropertiesFile))
}
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

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                username = "mapbox"

                password = mapboxProperties.getProperty("MAPBOX_DOWNLOADS_TOKEN")
            }
            authentication{
                create<BasicAuthentication>("basic")
            }
        }
    }
}

rootProject.name = "SPEED"
include(":app")
