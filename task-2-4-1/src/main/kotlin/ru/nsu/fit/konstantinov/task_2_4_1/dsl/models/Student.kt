package ru.nsu.fit.konstantinov.task_2_4_1.dsl.models

data class Student(
    var nickName: String,
    var name: String,
    var repoUrl: String,
    var group: Group,
    var tasks: List<GivenTask>,
    val lessons: ArrayList<Lesson>,
    val marks: List<Mark>
)
