package ru.nsu.fit.konstantinov.task_1_4_1


class GradeBook(
    val id: Int, val fullName: String, val faculty: String, val specialty: String, private val semestersCount: Int
) {
    enum class SubjectType {
        EXAM, CREDIT, DIFF_CREDIT
    }

    interface Grade {
        var grade: Int
        fun validateSubject(subject: Subject)
    }

    enum class SimpleGrade(override var grade: Int) : Grade {
        NOT_PASSED(0), PASSED(5);

        override fun validateSubject(subject: Subject) {
            if ((subject.subjectType == SubjectType.DIFF_CREDIT) || (subject.subjectType == SubjectType.EXAM)) {
                throw Exception("Illegal grade for this type of subject.")
            }
        }
    }

    enum class ComplexGrade(override var grade: Int) : Grade {
        POOR(2), SATISFACTORY(3), GOOD(4), EXCELLENT(5);

        override fun validateSubject(subject: Subject) {
            if ((subject.subjectType == SubjectType.CREDIT)) {
                throw Exception("Illegal grade for this type of subject.")
            }
        }
    }

    data class Subject(var subjectType: SubjectType, var grade: Grade)

    private val semesters: MutableList<Semester> = mutableListOf<Semester>().apply {
        for (i in 1..semestersCount) {
            this.add(Semester())
        }
    }

    fun addGrade(subject: String, grade: Grade, semesterNumber: Int, subjectType: SubjectType) {
        semesters[semesterNumber - 1].semesterGrades[subject] = Subject(subjectType, grade)
        semesters[semesterNumber - 1].semesterGrades[subject]?.let { grade.validateSubject(it) }
    }

    fun getGrade(subject: String, semesterNumber: Int) = semesters[semesterNumber - 1].semesterGrades[subject]?.grade

    fun getSemesterGrades(semesterNumber: Int): ArrayList<Grade> {
        val arrayList: ArrayList<Grade> = arrayListOf()
        for (i in semesters[semesterNumber - 1].semesterGrades.values) {
            arrayList.add(i.grade)
        }
        return arrayList
    }

    fun getSemesterSubjects(semesterNumber: Int) = semesters[semesterNumber - 1].semesterGrades.keys

    fun getAverageMark(): Double {
        var sum = 0.0
        var count = 0.0
        for (i in semesters) {
            for (j in i.semesterGrades.values) {
                if (j.subjectType != SubjectType.CREDIT) {
                    sum += j.grade.grade
                    count += 1
                }
            }
        }
        return sum / count
    }

    fun bigScholarshipInNextSemester(semesterNumber: Int): Boolean {
        var sum = 0.0
        var count = 0.0
        for (i in semesters[semesterNumber].semesterGrades.values) {
            if (i.subjectType != SubjectType.CREDIT) {
                sum += i.grade.grade
            }
        }
        count += semesters[semesterNumber].semesterGrades.values.size
        return (sum / count) == 5.0
    }

    fun redDiploma(): Boolean {
        for (i in semesters) {
            for (j in i.semesterGrades.values) {
                if (j.grade.grade == 3) {
                    return false
                }
            }
        }
        if (getAverageMark() < 4.75) {
            return false
        }
        return true
    }

    fun printGradeBook() {
        print("GradeBook\n")
        print("ID: $id, Full Name: $fullName, Faculty: $faculty, Specialty: $specialty\n")
        for ((count, _) in semesters.withIndex()) {
            print("=====================\n")
            print("Number of semester: ${count + 1}\n")
            for (j in semesters[count].semesterGrades) {
                println(j.key + ": " + j.value.subjectType + " " + j.value.grade)
            }
            print("=====================\n")
        }
    }

    fun getSubjectType(subject: String, semester: Int) = semesters[semester - 1].semesterGrades[subject]?.subjectType

    fun getSubjectsWithType(semester: Int, subjectType: SubjectType): ArrayList<String> {
        val arrayList = arrayListOf<String>()
        for (i in semesters[semester - 1].semesterGrades) {
            if (i.value.subjectType == subjectType) {
                arrayList.add(i.key)
            }
        }
        return arrayList
    }

    private data class Semester(val semesterGrades: HashMap<String, Subject> = HashMap())
}
