import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream
import java.net.URL
plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.dokka") version "1.8.10"
}

fun cmd(vararg args: String): String? {
    val output = ByteArrayOutputStream()
    val r = exec {
        this.commandLine(args.toList())
        this.isIgnoreExitValue = true
        this.standardOutput = output
        this.errorOutput = ByteArrayOutputStream()
    }
    return if (r.exitValue == 0) output.toByteArray().decodeToString().trim()
    else null
}

val tag = cmd("git", "describe", "--tags", "HEAD")
val hash = cmd("git", "rev-parse", "--short", "HEAD")!!
val isSnapshot = tag != null && hash !in tag
group = "moe.nea.jdwp"
version = tag ?: hash

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation(kotlin("test"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
tasks.test {
    useJUnitPlatform()
}


tasks.dokkaHtml {
    dokkaSourceSets {
        named("main") {
            moduleName.set("JDWP-ProtocolLib")
            sourceLink {
                localDirectory.set(file("src/main/"))
                remoteUrl.set(URL("https://github.com/romangraef/jdwp-protocol-lib/blob/$hash/src/main/"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}
