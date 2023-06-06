package ru.nsu.fit.konstantinov.task_2_4_1.utils.html

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Student

class GroupReportHTMLCreator {
    companion object {
        fun create(
            groupName: String,
            studentsInGroup: ArrayList<Student>,
            studentTestMap: MutableMap<String, ArrayList<Map<String, Boolean>>>
        ): String {
            val string = StringBuilder()
            string.appendHTML().html {
                head {
                }
                body {
                    style {
                        unsafe {
                            raw(
                                "table, th, td {" +
                                        "border: 1px solid black;" +
                                        "border-collapse: collapse;" +
                                        "}" +
                                        "td {" +
                                        "min-width: 100px;" +
                                        "border: 1px solid black;" +
                                        "}" +
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
                    table {
                        tr {
                            td {
                                this.colSpan = "6"
                                +groupName
                            }
                        }
                        tr {
                            td {
                                +"Student"
                            }
                            td {
                                +"Task"
                            }
                            td {
                                +"Build"
                            }
                            td {
                                +"Documentation"
                            }
                            td {
                                +"Coverage"
                            }
                            td {
                                +"Result"
                            }
                        }
                        studentsInGroup.forEach { student ->
                            var i = 0
                            studentTestMap[student.name]!!.forEach { task ->
                                for (curTask in task.keys) {
                                    tr {
                                        if (i == 0) {
                                            td {
                                                rowSpan = student.tasks.size.toString()
                                                +student.name
                                            }
                                        }
                                        td {
                                            +curTask
                                        }
                                        td {
                                            if (task[curTask] != null) {
                                                +"SUCCESS"

                                            } else {
                                                +"FALSE"
                                            }
                                        }
                                        td {
                                            +"+"
                                        }
                                        td {
                                            +"+"
                                        }
                                        td {
                                            if (task[curTask] != null) {
                                                +"1"
                                            } else {
                                                +"0"
                                            }
                                        }
                                    }
                                    i++
                                }
                            }
                        }
                    }
                }
            }
            return string.toString()
        }
    }
}
