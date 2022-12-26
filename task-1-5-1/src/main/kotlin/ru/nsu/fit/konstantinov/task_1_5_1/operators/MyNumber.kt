package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.atan2
import kotlin.math.sqrt

class MyNumber(override var token: String = "") : Operator {
    var imaginary: Double = 0.0
    var real: Double = 0.0

    constructor (a: Double, b: Double) : this() {
        real = a
        imaginary = b
    }

    override fun calculate(): MyNumber = this.apply { parseNumber(token) }

    private fun parseNumber(operand: String) {
        if (operand.contains("i")) {
            val complexNumber = operand.split("+").toTypedArray()
            if (complexNumber.size == 1 || complexNumber.size == 2) {
                imaginary = if (complexNumber[complexNumber.size - 1].length == 1) {
                    1.0
                } else {
                    complexNumber[complexNumber.size - 1].replace("i", "").toDouble()
                }
                if (complexNumber.size == 2) {
                    check(!complexNumber[0].contains("i")) { "Real part of complex number contains \"i\"." }
                    real = complexNumber[0].toDouble()
                }
            } else {
                throw IllegalStateException("Complex number is incorrect.")
            }
        } else {
            real = operand.toDouble()
        }
    }

    fun arg(): Double = atan2(imaginary, real)

    fun complexABS(): Double = if (real != 0.0 || imaginary != 0.0) {
        sqrt(real * real + imaginary * imaginary)
    } else {
        0.0
    }
}
