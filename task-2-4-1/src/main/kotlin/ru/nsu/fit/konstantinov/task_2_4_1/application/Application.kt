package ru.nsu.fit.konstantinov.task_2_4_1.application

import ru.nsu.fit.konstantinov.task_2_4_1.attendance.Attendance
import ru.nsu.fit.konstantinov.task_2_4_1.builder.Builder
import ru.nsu.fit.konstantinov.task_2_4_1.config.MakeConfig
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Student
import ru.nsu.fit.konstantinov.task_2_4_1.git.WorkWithGit
import ru.nsu.fit.konstantinov.task_2_4_1.report.Report
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    System.setProperty("idea.use.native.fs.for.win", "false")

    if (args.isEmpty()) {
        showHelpMessage()
        return
    }

    when (args[0]) {
        "-makeConfig" ->
            Application.makeConfig()

        "-printConfig" ->
            Application.printConfig(args)

        "-clone" ->
            Application.cloneRepo(args)

        "-pull" ->
            Application.pullRepo(args)

        "-test" ->
            Application.buildLab(args)

        "-documentation" ->
            Application.makeDocumentation(args)

        "-attendance" ->
            Application.addLesson(args)

        "-codestyle" ->
            Application.checkCodeStyle(args)

        "-codeCoverage" ->
            Application.checkCodeCoverage(args)

        "-report" ->
            Application.makeReport(args)

        else ->
            showHelpMessage()
    }
}

private fun showHelpMessage() {
    println(
        """
            -makeConfig - make configuration file
            -printConfig [name] - print configuration [student's name] file 
            -clone [name] - clone [student's] repository
            -pull [name] [branch] - pull [branch] from [student's name] repository 
            -test [name] [laboratory work] - build [student's name] [laboratory work]. Creates report in ./reports/[name] directory
            -documentation [name] [laboratory work] - make documentation about [student's name] [laboratory work]
            -attendance [date] [group name] [laboratory work] - check attendance of all students from [group name] at lesson [date] with given task [laboratory work]
            -codestyle [name] [laboratory work] - check code style for [student's name] [laboratory work]
            -codeCoverage [name] [laboratory work] - check code coverage of the unit test for [student's name] [laboratory work]
            -report [name] - make report about [student's name]
        """.trimIndent()
    )
}

class Application {
    companion object {
        fun printConfig(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: java.lang.IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            val path = "./configs/$name.kts"
            if (!File(path).exists()) {
                println("No such config. Try again")
            }
            val student = configureStudent(name)
            println(student)
        }

        fun cloneRepo(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            val workWithGit = WorkWithGit()
            workWithGit.cloneRepo(name)
            println("Successfully cloned repo")
        }

        fun makeConfig() {
            val makeConfig = MakeConfig()
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
                makeConfig.setUpStudent("$name", "$nickname", "$repoUrl", group)
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
            println("Successfully made a config")
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
            val workWithGit = WorkWithGit()
            workWithGit.pullRepo(name, branchName)
            println("Successfully pulled repo")
        }

        fun buildLab(args: Array<String>) {
            val builder = Builder()
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
            builder.buildLab(name, lab)
            println("Successfully tested this lab")
        }

        fun makeDocumentation(args: Array<String>) {
            val builder = Builder()
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
            builder.documentation(name, lab)
            println("Successfully made documentation for this lab")
        }

        fun addLesson(args: Array<String>) {
            val attendance = Attendance()
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
            attendance.addLesson(name, groupName, task)
        }

        fun checkCodeStyle(args: Array<String>) {
            val builder = Builder()
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
            builder.checkCodeStyle(name, lab)
            println("Successfully checked code style")
        }

        fun checkCodeCoverage(args: Array<String>) {
            val builder = Builder()
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
            builder.checkTestCoverage(name, lab)
            println("successfully ran with coverage")
        }

        private fun configureStudent(name: String): Student {
            val textConfig = File("./configs/$name.kts").readText()
            var scriptResult: Student
            with(ScriptEngineManager().getEngineByExtension("kts")) {
                scriptResult = eval(textConfig) as Student
            }
            return scriptResult
        }

        fun makeReport(args: Array<String>) {
            val name: String
            try {
                name = args[1]
            } catch (e: IndexOutOfBoundsException) {
                System.err.println("You didn't specify a name")
                return
            }
            val report = Report()
            report.makeReport(name)

        }
    }
}