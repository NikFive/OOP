package ru.nsu.fit.konstantinov.task_2_4_1.application.report

import ru.nsu.fit.konstantinov.task_2_4_1.application.config.Config
import ru.nsu.fit.konstantinov.task_2_4_1.application.gradle.Gradle
import ru.nsu.fit.konstantinov.task_2_4_1.application.repository.Repository
import ru.nsu.fit.konstantinov.task_2_4_1.utils.html.GroupReportHTMLCreator
import java.io.File

class GroupReport {
    fun createGroupReport(groupName: String) {
        val file = File("./reports/$groupName/report.html")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            file.createNewFile()
        }
        val studentsInGroup = Config().readGroupConfigs(groupName)
        if (studentsInGroup.isEmpty()) {
            println("You don't have students of this group")
            return
        }
        studentsInGroup.forEach {
            Repository().cloneRepo(it.name)
        }
        val studentTestMap: MutableMap<String, ArrayList<Map<String, Boolean>>> = mutableMapOf()
        studentsInGroup.forEach {
            studentTestMap[it.name] = Gradle.testAll(it.tasks, it.name)
            Gradle.documentationAll(it.tasks, it.name)
            Gradle.coverageAll(it.tasks, it.name)
        }
        file.writeText(GroupReportHTMLCreator.create(groupName, studentsInGroup, studentTestMap))
    }
}
