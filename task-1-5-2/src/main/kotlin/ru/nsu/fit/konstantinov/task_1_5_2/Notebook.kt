package ru.nsu.fit.konstantinov.task_1_5_2

import ru.nsu.fit.konstantinov.task_1_5_2.model.Note
import java.util.*

class Notebook {
    var notesTreeMap = TreeMap<Long, ArrayList<Note>>()
    fun add(vararg notes: String) {
        notes.forEach { i ->
            val time = System.currentTimeMillis()
            if (notesTreeMap.containsKey(time)) {
                notesTreeMap[time]?.add(Note(text = i, time = time))
            } else {
                notesTreeMap[time] = arrayListOf(Note(text = i, time = time))
            }
        }
    }

    fun rm(note: String) {
        notesTreeMap.forEach { i ->
            i.value.removeIf { it.text == note }
        }
    }

    fun show(): ArrayList<String> {
        val result = ArrayList<String>()
        notesTreeMap.forEach { i ->
            i.value.forEach { j -> result.add(j.text) }
        }
        return result
    }
}
