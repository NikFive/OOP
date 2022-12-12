package ru.nsu.fit.konstantinov.task_1_4_1


class GradeBook(
    val id: Int, val fullName: String, val faculty: String, val specialty: String, private val semestersCount: Int
) {
    enum class SubjectType {
        EXAM, CREDIT, DIFF_CREDIT
    }

    interface Grade {
        val grade: Int
    }

    enum class SimpleGrade : Grade {
        NOT_PASSED {
            override val grade: Int
                get() = 0
        },
        PASSED {
            override val grade: Int
                get() = 5
        };
    }

    enum class ComplexGrade : Grade {
        POOR {
            override val grade: Int
                get() = 2
        },
        SATISFACTORY {
            override val grade: Int
                get() = 3
        },
        GOOD {
            override val grade: Int
                get() = 4
        },
        EXCELLENT {
            override val grade: Int
                get() = 5
        };
    }

    data class Subject(
        val name: String, val subjectType: SubjectType, val grade: Grade
    )

    private val semesters: MutableList<Semester> = mutableListOf<Semester>().apply {
        for (i in 1..semestersCount) {
            this.add(Semester())
        }
    }

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

    fun printGradeBook() {
        var count = 0
        print("GradeBook\n")
        print("ID: $id, Full Name: $fullName, Faculty: $faculty, Specialty: $specialty\n")
        for (i in semesters) {
            print("=====================\n")
            print("Number of semester: ${count + 1}\n")
            for (j in semesters[count].semesterGrades) {
                println(j.key + ": " + j.value)
            }
            count++
            print("=====================\n")
        }
    }

    private data class Semester(val semesterGrades: HashMap<String, Subject> = HashMap())
}
