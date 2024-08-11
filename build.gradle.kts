plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
}

group = "com.debugdesk.superheros"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
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

}
