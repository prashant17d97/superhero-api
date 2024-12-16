plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    alias(libs.plugins.shadow) // Reference the Shadow plugin from the version catalog
}

group = "com.debugdesk.superheros"
version = "0.0.1"

application {
    mainClass.set("com.debugdesk.superheros.ApplicationKt") // Set the correct main class

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks {
    create("stage").dependsOn("installDist")

    shadowJar {
        archiveFileName.set("superheros-api-all.jar") // Create a fat JAR
    }
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
//    implementation(libs.kotlin.serialization.json)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.call.logging)
    implementation(libs.status.pages)
    implementation(libs.content.negotiation)
    implementation(libs.client.content.negotiation)
    implementation(libs.default.headers)
    testImplementation(libs.server.tests)
    implementation(libs.koin.ktor)
    implementation(libs.slf4j)
}
