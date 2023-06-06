package ru.nsu.fit.konstantinov.task_2_4_1.application.report

import ru.nsu.fit.konstantinov.task_2_4_1.application.config.Config
import ru.nsu.fit.konstantinov.task_2_4_1.application.gradle.Gradle
import ru.nsu.fit.konstantinov.task_2_4_1.utils.html.StudentReportHTMLCreator
import java.io.File

class StudentReport {
    fun createStudentReport(name: String) {
        val file = File("./reports/$name/report.html")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            file.createNewFile()
        }
        val scriptResult = Config().readConfig("$name.kts")
        file.writeText(StudentReportHTMLCreator.create(scriptResult, Gradle.testAll(scriptResult.tasks, name)))
    }
}
