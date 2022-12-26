package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.ln

class Log : Operator {
    override var token = "log"

    override fun calculate(): MyNumber {
        val first = provideArguments(1)[0]
        return MyNumber(ln(first.complexABS()), first.arg())
    }

}
