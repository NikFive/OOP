package ru.nsu.fit.konstantinov.task_2_4_1.application.gradle

import org.gradle.tooling.GradleConnector
import ru.nsu.fit.konstantinov.task_2_4_1.application.report.CoverageReport
import ru.nsu.fit.konstantinov.task_2_4_1.application.report.DocumentationReport
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.GivenTask
import java.io.File
import java.io.FileNotFoundException

class Gradle {
    companion object {
        fun test(name: String, lab: String): Boolean {
            if (!File("./repos/$name/$lab").exists()) {
                throw FileNotFoundException("$name doesn't have $lab")
            }
            val connection =
                GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$lab")).connect()
            connection.use {
                it.newBuild().forTasks("test").run()
                println("Successful test")
            }
            return true
        }

        fun testAll(givenTasks: List<GivenTask>, name: String): ArrayList<Map<String, Boolean>> {
            val tasks = ArrayList<Map<String, Boolean>>()
            givenTasks.forEach {
                tasks.add(mapOf(it.taskId to test(name, it.taskId)))
            }
            return tasks
        }

        fun documentation(name: String, lab: String) {
            if (!File("./repos/$name/$lab").exists()) {
                throw FileNotFoundException("$name doesn't have such file")
            }
            val connection =
                GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$lab")).connect()
            connection.use {
                it.newBuild().forTasks("javadoc").run()
            }
            DocumentationReport.create(name, lab)
        }

        fun documentationAll(givenTasks: List<GivenTask>, name: String) {
            givenTasks.forEach {
                documentation(name, it.taskId)
            }
        }

        fun coverage(name: String, lab: String) {
            if (!File("./repos/$name/$lab").exists()) {
                throw FileNotFoundException("$name doesn't have such file")
            }
            val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$lab")).connect()
            connection.use {
                it.newBuild().forTasks("jacocoTestReport").run()
            }
            CoverageReport.create(name, lab)
        }

        fun coverageAll(givenTasks: List<GivenTask>, name: String) {
            givenTasks.forEach {
                coverage(name, it.taskId)
            }
        }
    }
}
