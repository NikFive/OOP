package ru.nsu.fit.konstantinov.task_1_4_1


class GradeBook(
    val id: Int, val fullName: String, val faculty: String, val specialty: String
) {
    private val semesters: MutableList<Semester> = mutableListOf(
        Semester(), Semester(), Semester(), Semester(), Semester(), Semester(), Semester(), Semester()
    )

    fun addGrade(subject: String, grade: Int, semesterNumber: Int) {
        semesters[semesterNumber - 1].semesterGrades[subject] = grade
    }

    fun getGrade(subject: String, semesterNumber: Int) = semesters[semesterNumber - 1].semesterGrades[subject]

    fun getSemesterGrades(semesterNumber: Int) = semesters[semesterNumber - 1].semesterGrades.values

    fun getSemesterSubjects(semesterNumber: Int) = semesters[semesterNumber - 1].semesterGrades.keys

    fun getAverageMark(): Double {
        var sum = 0.0
        var count = 0.0
        for (i in semesters) {
            sum += i.semesterGrades.values.sum()
            count += i.semesterGrades.values.size
        }
        return sum / count
    }

    fun bigScholarshipInNextSemester(semesterNumber: Int): Boolean {
        var sum = 0.0
        var count = 0.0
        sum += semesters[semesterNumber].semesterGrades.values.sum()
        count += semesters[semesterNumber].semesterGrades.values.size
        return (sum / count) == 5.0
    }

    fun redDiploma(): Boolean {
        for (i in semesters) {
            if (i.semesterGrades.values.contains(3)) {
                return false
            }
        }
        if (getAverageMark() < 4.75) {
            return false
        }
        return true
    }

    private data class Semester(val semesterGrades: HashMap<String, Int> = HashMap())
}
