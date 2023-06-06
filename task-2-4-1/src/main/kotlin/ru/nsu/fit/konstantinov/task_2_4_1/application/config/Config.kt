package ru.nsu.fit.konstantinov.task_2_4_1.application.config

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Group
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Student
import java.io.File
import javax.script.ScriptEngineManager

class Config {
    fun createConfig(name: String, nickname: String, repoUrl: String, group: Int) {
        val file = File("./configs/$name.kts")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            file.createNewFile()
        }
        if (file.length() == 0L) {
            file.writeText(
                """
                import ru.nsu.fit.konstantinov.task_2_4_1.dsl.DSL
                
                DSL().student {
                       nickName = "$nickname"
                       name = "$name"
                       repoUrl = "$repoUrl"
                       group {
                            name = $group
                       }
                       tasks {
		                    /*
		                    write given tasks for this student here
		                    like
                            givenTask {
                                taskId = "task_2_4_1"
                                deadLine = "dd-MM-yyyy"
                            }
		                    */
	                    }
	                   lessons {
		                    /*
		                    write lessons with attendance of this student here
		                    like
                            lesson {
                                attendance = true
                                date = "dd-MM-yyyy"
                            }
		                    */
	                   }
	                   marks {
		                    /*
		                    write marks of this student here
		                    like
                            mark {
                                name = 5
                                date = "dd-MM-yyyy"
                            }
		               */
	                   }
                }
            """.trimIndent()
            )
        } else {
            println("Config already exists")
        }
    }

    fun readConfig(name: String): Student {
        val textConfig = File("./configs/$name").readText()
        var scriptResult: Student
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Student
        }
        return scriptResult
    }

    fun readGroupConfigs(groupName: String): ArrayList<Student> {
        val configFiles = ArrayList<File>()
        File("./configs").walk().forEach {
            configFiles.add(it)
        }
        val allStudents = ArrayList<Student>()
        configFiles.forEach {
            if (it.name != "configs") {
                allStudents.add(readConfig(it.name))
            }
        }
        val studentsInGroup = ArrayList<Student>()
        val group = Group(groupName.toInt())
        allStudents.forEach {
            if (it.group == group) {
                studentsInGroup.add(it)
            }
        }
        return studentsInGroup
    }
}
