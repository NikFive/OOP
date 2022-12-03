package ru.nsu.fit.konstantinov.task_1_4_1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GradeBookTest {
    @Test
    fun simpleTest() {
        val me = GradeBook(210643, "Konstantinov Nikita Igorevich", "FIT", "СSaSD", 8)
        me.addGrade("Math", 4, 1)
        me.addGrade("History", 4, 1)
        me.addGrade("Haskell", 5, 1)
        me.addGrade("C", 5, 1)
        assertEquals("Konstantinov Nikita Igorevich", me.fullName)
        assertEquals("FIT", me.faculty)
        assertEquals("СSaSD", me.specialty)
        assertEquals(210643, me.id)
        assertEquals(5, me.getGrade("Haskell", 1))
        assertEquals(mutableListOf(5, 5, 4, 4), me.getSemesterGrades(1).toMutableList())
        assertEquals(mutableSetOf("Math", "History", "Haskell", "C"), me.getSemesterSubjects(1))
        assertEquals(4.5, me.getAverageMark())
        assertEquals(false, me.bigScholarshipInNextSemester(1))
        assertEquals(false, me.redDiploma())
        me.printGradeBook()
    }
}
