package ru.nsu.fit.konstantinov.task_1_5_2.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(val text: String, val time: Long)
