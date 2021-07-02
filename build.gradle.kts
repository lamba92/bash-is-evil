plugins {
    kotlin("jvm") version "1.5.20"
    application
    `docker-plugin`
}

application {
    mainClass.set("com.github.lamba92.evilbash.MainKt")
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

docker {
    images {

        all {
            version = "42"
            context {
                from("Dockerfile", tasks.installDist)
            }
            buildArgs = mapOf("ARG_APP_NAME" to project.name)
        }

        register("production") {
            imageName = "${project.name}-prod"
        }

        register("staging") {
            imageName = "${project.name}-staging"
        }

    }
}
