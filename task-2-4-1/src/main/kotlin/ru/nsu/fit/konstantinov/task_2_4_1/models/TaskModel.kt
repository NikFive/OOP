package ru.nsu.fit.konstantinov.task_2_4_1.models

data class TaskModel(
    val id: String? = null,
    val name: String? = null,
    val score: Double? = null,
    val isActive: Boolean = false,
    val isTested: Boolean = false,
)
