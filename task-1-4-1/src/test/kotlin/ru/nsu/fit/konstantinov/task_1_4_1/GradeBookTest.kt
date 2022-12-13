package ru.nsu.fit.konstantinov.task_1_4_1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GradeBookTest {
    @Test
    fun simpleTest() {
        val me = GradeBook(210643, "Konstantinov Nikita Igorevich", "FIT", "СSaSD", 8)
        me.addGrade("Math", GradeBook.SimpleGrade.NOT_PASSED, 1, GradeBook.SubjectType.CREDIT)
        me.addGrade("History", GradeBook.ComplexGrade.EXCELLENT, 1, GradeBook.SubjectType.EXAM)
        me.addGrade("Haskell", GradeBook.ComplexGrade.GOOD, 1, GradeBook.SubjectType.DIFF_CREDIT)
        me.addGrade("C", GradeBook.ComplexGrade.SATISFACTORY, 1, GradeBook.SubjectType.EXAM)
        assertEquals("Konstantinov Nikita Igorevich", me.fullName)
        assertEquals("FIT", me.faculty)
        assertEquals("СSaSD", me.specialty)
        assertEquals(210643, me.id)
        assertEquals(GradeBook.ComplexGrade.GOOD, me.getGrade("Haskell", 1))
        val grades: MutableList<GradeBook.Grade> = mutableListOf(
            GradeBook.ComplexGrade.SATISFACTORY,
            GradeBook.ComplexGrade.GOOD,
            GradeBook.SimpleGrade.NOT_PASSED,
            GradeBook.ComplexGrade.EXCELLENT,
        )
        assertEquals(grades, me.getSemesterGrades(1).toMutableList())
        assertEquals(mutableSetOf("Math", "History", "Haskell", "C"), me.getSemesterSubjects(1))
        assertEquals(4.0, me.getAverageMark())
        assertEquals(false, me.bigScholarshipInNextSemester(1))
        assertEquals(false, me.redDiploma())
        assertEquals(GradeBook.SubjectType.EXAM, me.getSubjectType("C", 1))
        assertEquals(arrayListOf("C", "History"), me.getSubjectsWithType(1, GradeBook.SubjectType.EXAM))
        me.printGradeBook()
    }
}
