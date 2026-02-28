plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    `maven-publish`
    signing
}

android {
    namespace = "com.rejowan.ccpc"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {

    api(libs.libphonenumber.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}


publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.rejowan"
            artifactId = "ccpc"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("CountryCodePickerCompose")
                description.set("A modern, customizable Country Code Picker for Jetpack Compose with Material 3 design")
                url.set("https://github.com/ahmmedrejowan/CountryCodePickerCompose")
                inceptionYear.set("2024")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("ahmmedrejowan")
                        name.set("K M Rejowan Ahmmed")
                        email.set("ahmmedrejowan@gmail.com")
                        url.set("https://github.com/ahmmedrejowan")
                    }
                }

                scm {
                    url.set("https://github.com/ahmmedrejowan/CountryCodePickerCompose")
                    connection.set("scm:git:git://github.com/ahmmedrejowan/CountryCodePickerCompose.git")
                    developerConnection.set("scm:git:ssh://git@github.com/ahmmedrejowan/CountryCodePickerCompose.git")
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/ahmmedrejowan/CountryCodePickerCompose/issues")
                }
            }
        }
    }

    // Publish to local staging directory, then upload manually to Central Portal
    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

// Signing
the<SigningExtension>().apply {
    sign(the<PublishingExtension>().publications)
}

// Task to create a bundle zip for Central Portal upload
tasks.register<Zip>("createCentralBundle") {
    dependsOn("publishReleasePublicationToStagingRepository")

    from(layout.buildDirectory.dir("staging-deploy"))
    archiveFileName.set("ccpc-1.0.0-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("central-bundle"))
}