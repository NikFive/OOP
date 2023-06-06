package ru.nsu.fit.konstantinov.task_2_4_1.application.report

import ru.nsu.fit.konstantinov.task_2_4_1.utils.FileCopier
import java.io.File
import java.nio.file.Path

class DocumentationReport {
    companion object {
        fun create(name: String, lab: String) {
            val directory = File("./reports/$name/$lab/documentation")
            if (!directory.parentFile.exists()) directory.parentFile.mkdirs()
            val dir = Path.of("./reports/$name/$lab/documentation")
            val origin = Path.of("./repos/$name/$lab/build/docs/javadoc")
            FileCopier.copyAll(directory, dir, origin)
            val documentationFile = File("./reports/$name/$lab/documentation/allclasses-index.html")
            documentationFile.renameTo(File("./reports/$name/$lab/documentation/Documentation.html"))
        }
    }
}
