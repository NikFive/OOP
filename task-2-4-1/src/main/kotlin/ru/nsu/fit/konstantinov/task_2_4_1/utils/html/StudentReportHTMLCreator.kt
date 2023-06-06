package ru.nsu.fit.konstantinov.task_2_4_1.utils.html

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Student
import java.time.LocalDate

class StudentReportHTMLCreator {
    companion object {
        fun create(scriptResult: Student, tasksMap: ArrayList<Map<String, Boolean>>): String {
            val string = StringBuilder()
            string.appendHTML().html {
                head {
                }
                body {
                    style {
                        unsafe {
                            raw(
                                ".green {" +
                                        "color: green;" +
                                        "}" +
                                        ".red {" +
                                        "color: red;" +
                                        "}" +
                                        ".orange {" +
                                        "color: orange;" +
                                        "}" +
                                        ".blue {" +
                                        "color: blue;" +
                                        "}"
                            )
                        }
                    }
                    h1 { +"Report by ${LocalDate.now()}" }
                    h2 {
                        +"Student ${scriptResult.name}. Group: ${scriptResult.group.number}"
                    }
                    h3 {
                        +"Nickname: ${scriptResult.nickName}"
                    }
                    h3 {
                        +"Repository: ${scriptResult.repoUrl}"
                    }
                    h3 {
                        +"Attendance"
                    }
                    scriptResult.lessons.forEach {
                        div {
                            +"*${it.date} "
                            span(
                                if (it.attendance) {
                                    "green"
                                } else {
                                    "red"
                                }
                            ) {
                                +if (it.attendance) "ATTENDED" else "ABSENT"
                            }
                        }
                    }
                    h3 {
                        +"Laboratory works"
                    }
                    tasksMap.forEach {
                        div {
                            it.keys.forEach {
                                +"$it result "
                            }
                            span {
                                it.values.forEach {
                                    span(
                                        if (it) {
                                            "green"
                                        } else {
                                            "red"
                                        }
                                    ) {
                                        +if (it) "SUCCESSFUL BUILDING" else "SOMETHING WENT WRONG"
                                    }

                                }
                            }
                        }
                    }
                    h3 {
                        +"Marks"
                    }
                    scriptResult.marks.forEach {
                        div {
                            +"*${it.date} mark "
                            span(
                                when (it.name) {
                                    5 -> {
                                        "green"
                                    }

                                    4 -> {
                                        "blue"
                                    }

                                    3 -> {
                                        "orange"
                                    }

                                    else -> {
                                        "red"
                                    }
                                }
                            ) {
                                +"${it.name}"
                            }
                        }
                    }
                }
            }
            return string.toString()
        }
    }
}
