package com.github.lamba92.evilbash.gradle

import org.gradle.api.*
import org.gradle.api.file.CopySourceSpec
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.*

class DockerPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val ext = extensions.create<DockerExtension>(
            "docker",
            container { name ->
                DockerImage(
                    name = name,
                    version = project.version.toString(),
                    imageName = name
                )
            }
        )
        afterEvaluate {
            ext.images.forEach { data: DockerImage ->

                val capitalizedName = data.name.capitalize()
                val dockerPrepare = tasks.register<Sync>("docker${capitalizedName}Prepare") {
                    group = "docker"
                    data.copySourceSpec.execute(this)
                    into("$buildDir/docker/${data.name}")
                }

                val imageTag = "${data.imageName.toLowerCase()}:${data.version}"
                val dockerBuild = tasks.register<Exec>("docker${capitalizedName}Build") {
                    dependsOn(dockerPrepare)
                    group = "docker"
                    executable = "docker"
                    setArgs(buildList {
                        add("build")
                        data.buildArgs.forEach { (key, value) ->
                            addAll("--build-arg", "$key=$value")
                        }
                        addAll("-t", imageTag)
                        add(dockerPrepare.get().destinationDir.absolutePath)
                    })
                }

                val dockerStop = tasks.register<Exec>("docker${capitalizedName}Stop") {
                    group = "docker"
                    executable = "docker"
                    setArgs(listOf("stop", data.containerName))
                    isIgnoreExitValue = true
                }

                tasks.register<Exec>("docker${capitalizedName}Start") {
                    dependsOn(dockerBuild, dockerStop)
                    group = "docker"
                    executable = "docker"
                    setArgs(listOf(
                        "run",
                        "--rm",
                        "-d",
                        "--name",
                        data.containerName,
                        "-p",
                        "8081:8081",
                        imageTag
                    ))
                }
            }
        }
    }
}

open class DockerExtension(val images: NamedDomainObjectContainer<DockerImage>)

class DockerImage(
    private val name: String,
    var version: String,
    var imageName: String
) : Named {

    internal var copySourceSpec: Action<CopySourceSpec> = Action { }

    var buildArgs = mapOf<String, String>()

    var containerName = "${name}Container"

    fun context(action: Action<CopySourceSpec>) {
        copySourceSpec = action
    }

    override fun getName() = name
}

fun <T> MutableList<T>.addAll(vararg items: T) =
    addAll(items)