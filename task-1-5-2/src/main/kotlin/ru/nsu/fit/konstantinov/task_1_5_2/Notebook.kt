package ru.nsu.fit.konstantinov.task_1_5_2

import ru.nsu.fit.konstantinov.task_1_5_2.model.Note
import java.text.SimpleDateFormat
import java.util.*

class Notebook {
    private var notesTreeMap = TreeMap<Long, ArrayList<Note>>()
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

    fun show(): MutableList<String> {
        val result = mutableListOf<String>()
        notesTreeMap.forEach { i ->
            i.value.forEach { j -> result.add(j.text) }
        }
        return result
    }

    fun show(from: String, to: String, vararg keyWords: String): MutableList<String> {
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS")
        val fromInLong = format.parse(from).time
        val toInLong = format.parse(to).time
        var result = mutableListOf<String>()
        notesTreeMap.forEach { i ->
            if (i.key in (fromInLong + 1) until toInLong) {
                i.value.forEach { j -> result.add(j.text) }
            }
        }
        if (keyWords.isNotEmpty()) {
            result = result.filter { containsKeyWord(it, keyWords) }.toMutableList()
        }
        return result
    }

    private fun containsKeyWord(text: String, keyWords: Array<out String>): Boolean {
        for (i in keyWords) {
            if (text.contains(i)) {
                return true
            }
        }
        return false
    }
}
