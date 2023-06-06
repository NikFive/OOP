package ru.nsu.fit.konstantinov.task_2_4_1

import ru.nsu.fit.konstantinov.task_2_4_1.application.Application
import ru.nsu.fit.konstantinov.task_2_4_1.utils.GradleTaskType

fun main(args: Array<String>) {
    System.setProperty("idea.use.native.fs.for.win", "false")
    if (args.isEmpty()) {
        showHelpMessage()
        return
    }
    when (args[0]) {
        "-createConfig" ->
            Application.createConfig()

        "-printConfig" ->
            Application.printConfig(args)

        "-clone" ->
            Application.cloneRepo(args)

        "-pull" ->
            Application.pullRepo(args)

        "-attendance" ->
            Application.addLesson(args)

        "-test" ->
            Application.executeGradleTask(args, GradleTaskType.TEST)

        "-documentation" ->
            Application.executeGradleTask(args, GradleTaskType.DOCUMENTATION)

        "-coverage" ->
            Application.executeGradleTask(args, GradleTaskType.COVERAGE)

        "-studentReport" ->
            Application.createStudentReport(args)

        "-groupReport" ->
            Application.createGroupReport(args)

        else ->
            showHelpMessage()
    }
    return
}

private fun showHelpMessage() {
    println(
        """
            -createConfig - make configuration file
            -printConfig [name] - print configuration [student's name] file 
            -clone [name] - clone [student's] repository
            -pull [name] [branch] - pull [branch] from [student's name] repository 
            -test [name] [laboratory work] - build [student's name] [laboratory work]. Creates report in ./reports/[name] directory
            -documentation [name] [laboratory work] - make documentation about [student's name] [laboratory work]
            -attendance [date] [group name] [laboratory work] - check attendance of all students from [group name] at lesson [date] with given task [laboratory work]
            -coverage [name] [laboratory work] - check code coverage of the unit test for [student's name] [laboratory work]
            -studentReport [name] - make report about [student's name]
            -groupReport [group name] - check attendance of all students from [group name]
        """.trimIndent()
    )
}
