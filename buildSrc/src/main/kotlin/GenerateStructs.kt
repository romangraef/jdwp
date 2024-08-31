import org.gradle.api.file.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*

abstract class GenerateStructs : Exec() {
	@get:InputFile
	abstract val domparserFile: RegularFileProperty

	init {
		domparserFile.convention(this.project.layout.projectDirectory.file("domparser.py"))
		outputs.cacheIf { true }
	}

	override fun exec() {
		environment("GEN_DOC_FILE", docFile.get().asFile.absolutePath)
		environment("GEN_BASE", javaFiles.get().asFile.absolutePath)
		environment("JDWP_BASE_URL", documentationBaseUrl.get())
		commandLine("python3", domparserFile.get().asFile.absolutePath)
		super.exec()
	}

	@get:OutputFile
	abstract val docFile: RegularFileProperty

	@get:OutputDirectory
	abstract val javaFiles: DirectoryProperty

	@get:Input
	abstract val documentationBaseUrl: Property<String>
}
