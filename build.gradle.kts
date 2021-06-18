plugins {
    kotlin("jvm") version "1.5.10"
}

group = "com.github.lamba92"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-cio:1.6.0")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}
