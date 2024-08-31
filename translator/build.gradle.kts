plugins {
	kotlin("jvm")
	java
}

dependencies {
	implementation(project(":"))
}
kotlin.jvmToolchain(21)
