package ru.nsu.fit.konstantinov.task_2_4_1.application

import ru.nsu.fit.konstantinov.task_2_4_1.application.attendance.Attendance
import ru.nsu.fit.konstantinov.task_2_4_1.application.config.Config
import ru.nsu.fit.konstantinov.task_2_4_1.application.gradle.Gradle
import ru.nsu.fit.konstantinov.task_2_4_1.application.report.GroupReport
import ru.nsu.fit.konstantinov.task_2_4_1.application.report.StudentReport
import ru.nsu.fit.konstantinov.task_2_4_1.application.repository.Repository
import ru.nsu.fit.konstantinov.task_2_4_1.utils.GradleTaskType
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class Application {
    companion object {
        fun createConfig() {
            println("Write student's name")
            val name = readlnOrNull()
            if (File("./configs/$name.kts").exists()) {
                println("You have such config file")
                return
            }
            println("Write student's nickname")
            val nickname = readlnOrNull()
            println("Write link of student's repo")
            val repoUrl = readlnOrNull()
            println("Write student's group")
            val group = readln().toInt()
            try {
                Config().createConfig("$name", "$nickname", "$repoUrl", group)
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
            println("Successfully made a config")
        }

        fun printConfig(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: java.lang.IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            if (!File("./configs/$name.kts").exists()) {
                println("No such config. Try again")
            }
            println(Config().readConfig(name))
        }

        fun cloneRepo(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            Repository().cloneRepo(name)
            println("Successfully cloned repo")
        }

        fun pullRepo(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify name")
                return
            }
            if (!Files.isDirectory(Paths.get("./repos/$name"))) {
                println("No such directory. Did you clone this repo?")
            }
            val branchName: String
            try {
                branchName = args[2]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a branch")
                return
            }
            Repository().pullRepo(name, branchName)
            println("Successfully pulled repo")
        }

        fun executeGradleTask(args: Array<String>, taskType: GradleTaskType) {
            val name: String
            val lab: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a student's name")
                return
            }
            try {
                lab = args[2]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a laboratory work")
                return
            }
            when (taskType) {
                GradleTaskType.TEST -> {
                    Gradle.test(name, lab)
                    println("Successfully tested this lab")
                }

                GradleTaskType.COVERAGE -> {
                    Gradle.coverage(name, lab)
                    println("successfully ran with coverage")
                }

                GradleTaskType.DOCUMENTATION -> {
                    Gradle.documentation(name, lab)
                    println("Successfully made documentation for this lab")
                }
            }
        }

        fun createStudentReport(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            StudentReport().createStudentReport(name)
        }

        fun createGroupReport(args: Array<String>) {
            val groupName: String
            try {
                groupName = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a group name")
                return
            }
            GroupReport().createGroupReport(groupName)
        }

        fun addLesson(args: Array<String>) {
            val name: String
            val groupName: String
            val task: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a date")
                return
            }
            try {
                groupName = args[2]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a student's name")
                return
            }
            try {
                task = args[3]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a student's name")
                return
            }
            Attendance().addLesson(name, groupName, task)
        }
    }
}
