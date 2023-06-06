package ru.nsu.fit.konstantinov.task_2_4_1.application.report

import ru.nsu.fit.konstantinov.task_2_4_1.utils.FileCopier
import java.io.File
import java.nio.file.Path

class CoverageReport {
    companion object {
        fun create(name: String, lab: String) {
            val directory = File("./reports/$name/$lab/coverage")
            if (!directory.parentFile.exists()) directory.parentFile.mkdirs()
            val dir = Path.of("./reports/$name/$lab/coverage")
            val origin = Path.of("./repos/$name/$lab/build/jacocoHtml")
            FileCopier.copyAll(directory, dir, origin)
        }
    }
}
