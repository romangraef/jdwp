plugins {
	kotlin("jvm")
	java
}

dependencies {
	implementation(project(":"))
	implementation("net.fabricmc:mapping-io:0.6.1")
}
kotlin.jvmToolchain(21)
