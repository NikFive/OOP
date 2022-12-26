package ru.nsu.fit.konstantinov.task_1_5_1

import ru.nsu.fit.konstantinov.task_1_5_1.calculator.Calculator
import ru.nsu.fit.konstantinov.task_1_5_1.my_operators.TernaryPlus
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {
    @Test
    fun testRealOperations() {
        assertEquals(5.0, Calculator.calculate("+ 2.0 3.0").real)
        assertEquals(10.0, Calculator.calculate("+ + 5.0 2.0 3.0").real)
        assertEquals(6.0, Calculator.calculate("+ - 5.0 2.0 3.0").real)
        assertEquals(13.0, Calculator.calculate("+ * 5.0 2.0 3.0").real)
        assertEquals(9.0, Calculator.calculate("* - 5.0 2.0 3.0").real)
        assertEquals(2.0, Calculator.calculate("sqrt 4").real)
        assertEquals(4.0, Calculator.calculate("^ 2 2").real)
        assertEquals(1.0, Calculator.calculate("/ 2 2").real)
        assertEquals(0.0, Calculator.calculate("sin 0").real)
        assertEquals(0.0, Calculator.calculate("cos rad 90").real)
        assertEquals(1.0, Calculator.calculate("log 2.718281828459045").real)
        assertEquals(0.0, Calculator.calculate("deg 0").real)
        assertEquals(0.0, Calculator.calculate("rad 0").real)
        assertEquals(PI, Calculator.calculate("rad 180").real)
    }

    @Test
    fun testImaginaryOperations() {
        assertEquals(5.0, Calculator.calculate("+ 2i 2+3i").imaginary)
        assertEquals(2.0, Calculator.calculate("+ 2i 2+3i").real)
        assertEquals(1000.0, Calculator.calculate("^ 10+4i 3").real)
        assertEquals(0.0, Calculator.calculate("^ 10+4i 3").imaginary)
    }

    @Test
    fun testOuterOperations() {
        val calculator = Calculator().apply { this.addOuterOperators(TernaryPlus()) }
        assertEquals(9.0, calculator.calculate("!+ 2 3 4").real)
    }
}
