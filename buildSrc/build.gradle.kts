plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.5.20"
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("dockerPlugin") {
            id = "docker-plugin"
            implementationClass = "com.github.lamba92.evilbash.gradle.DockerPlugin"
        }
    }
}

kotlin {
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
        }
    }
}