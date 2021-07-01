plugins {
    kotlin("jvm") version "1.5.20"
}

group = "com.github.lamba92"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-cio:1.6.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}