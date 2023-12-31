plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinCocoapods)
    id("org.jetbrains.dokka") version "0.9.17"
    id("maven-publish")
    kotlin("plugin.serialization")
}


@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    targetHierarchy.default()

    androidTarget {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
        }
        dependencies {
            implementation("io.ktor:ktor-client-darwin:2.2.4")

        }
    }


    sourceSets {
        val ktorVersion = "2.2.4"
        val coroutinesVersion = "1.6.4"
        val serializationVersion = "1.4.1"
        val commonMain by getting {
            dependencies{
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation(libs.kotlinx.serialization.json)
                implementation("io.ktor:ktor-client-darwin:2.2.4")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

    }

}

group = "com.example.mysharedlib"
version = "1.0.0"

val GITHUB_USER: String by project
val GITHUB_TOKEN: String by project

publishing {
    repositories {
        maven {
            setUrl("https://maven.pkg.github.com/bb609999/KmmLibExample")
            credentials {
                username = GITHUB_USER
                password = GITHUB_TOKEN
            }
        }
    }
}



android {
    namespace = "com.example.kmmlibexample"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
