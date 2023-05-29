package ru.nsu.fit.konstantinov.task_2_4_1.config

import java.io.File

class MakeConfig {
    fun setUpStudent(name: String, nickname: String, repoUrl: String, group: Int) {
        val file = File("./configs/$name.kts")
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
                                taskId = "task 2.4.1"
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
}