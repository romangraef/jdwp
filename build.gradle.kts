import java.io.ByteArrayOutputStream

plugins {
	`java-library`
	`maven-publish`
	kotlin("jvm") version "2.0.0"
	id("org.jetbrains.dokka") version "1.9.20"
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
allprojects {
	repositories {
		mavenLocal()
		mavenCentral()
	}
}
val generateStructs by tasks.register("generateStructs", GenerateStructs::class) {
	docFile.set(layout.buildDirectory.file("GeneratedDocs.md"))
	javaFiles.set(layout.buildDirectory.dir("generated/structs/kotlin"))
	documentationBaseUrl.set("https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html")
}

sourceSets.main {
	kotlin {
		this.srcDir(generateStructs.javaFiles)
	}
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
	testImplementation(kotlin("test"))
}

kotlin.jvmToolchain(21)
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
tasks.test {
	useJUnitPlatform()
}

tasks.dokkaHtml {
	dokkaSourceSets {
		named("main") {
			includes.from(file("Docs.md"), generateStructs.docFile)
			moduleName.set("JDWP-ProtocolLib")
			sourceLink {
				localDirectory.set(file("src/main/"))
				remoteUrl.set(uri("https://github.com/nea89o/jdwp-protocol-lib/blob/$hash/src/main/").toURL())
				remoteLineSuffix.set("#L")
			}
		}
	}
}
