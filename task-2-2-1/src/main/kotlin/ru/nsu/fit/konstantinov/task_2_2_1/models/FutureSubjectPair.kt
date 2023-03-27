package ru.nsu.fit.konstantinov.task_2_2_1.models

import java.util.concurrent.Future

data class FutureSubjectPair(val subject: Any, val future: Future<*>)
