package ru.nsu.fit.konstantinov.task_1_3_1

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FindSubString {
    private var states: HashMap<Int, HashMap<Char, Int>> = HashMap()
    private var bufferSize = 4096

    fun findSubStringInStreamKMP(stream: InputStream, subString: String): ArrayList<Int> {
        val result = arrayListOf<Int>()
        var currentLen = 0
        var size = 0
        val bufferedReader = stream.bufferedReader()
        val bufferSize = subString.length + 2
        var ending = charArrayOf()
        while (size != -1) {
            val buffer = CharArray(bufferSize)
            size = bufferedReader.read(buffer)
            val string = ending + buffer
            ending = buffer.drop(subString.length).toCharArray()
            result += if (currentLen != 0) {
                kmpAlgorithm(subString, string, currentLen - subString.length + 1)
            } else {
                kmpAlgorithm(subString, string, 0)
            }
            currentLen += bufferSize
        }
        return result
    }

    private fun kmpAlgorithm(subString: String, string: CharArray, shift: Int): ArrayList<Int> {
        val result = arrayListOf<Int>()
        val subStringLength = subString.length
        val stringLength = string.size

        val prefixArray = IntArray(subStringLength)
        computePrefixArray(subString, subStringLength, prefixArray)

        var j = 0
        var i = 0
        while (stringLength - i >= subStringLength - j) {
            if (subString[j] == string[i]) {
                j++
                i++
            }
            if (j == subStringLength) {
                result.add(i - j + shift)
                j = prefixArray[j - 1]
            } else if (i < stringLength && subString[j] != string[i]) {
                if (j != 0) j = prefixArray[j - 1] else i += 1
            }
        }
        return result
    }

    private fun computePrefixArray(subString: String, stringLength: Int, prefixArray: IntArray) {
        var length = 0
        var count = 1
        prefixArray[0] = 0
        while (count < stringLength) {
            if (subString[count] == subString[length]) {
                length++
                prefixArray[count] = length
                count++
            } else {
                if (length != 0) {
                    length = prefixArray[length - 1]
                } else {
                    prefixArray[count] = 0
                    count++
                }
            }
        }
    }

    fun findSubStringInStreamAho(stream: InputStream, subString: String): ArrayList<Int> {
        generateStates(subString)
        while (bufferSize < subString.length) {
            bufferSize *= 2
        }
        val bufferedReader = BufferedReader(InputStreamReader(stream), bufferSize)
        val result = ArrayList<Int>()
        var curStates = ArrayList<Int>()
        curStates.add(0)
        var counter = 0
        while (bufferedReader.ready()) {
            var newStates = ArrayList<Int>()
            val tmpChar = bufferedReader.read().toChar()
            for (state in curStates) {
                if (states[state]?.containsKey(tmpChar) == true) {
                    if (states[state]?.get(tmpChar) == subString.length) {
                        result.add(counter - subString.length + 1)
                        newStates = ArrayList()
                        break
                    }
                    states[state]?.get(tmpChar)?.let { newStates.add(it) }
                }
                if (state != 0 && states[0]?.containsKey(tmpChar) == true) {
                    states[0]?.get(tmpChar)?.let { newStates.add(it) }
                }
            }
            newStates.add(0)
            curStates = newStates
            counter++
        }
        return result
    }

    private fun generateStates(inputString: String) {
        for (i in inputString.indices) {
            val currentStateMap = HashMap<Char, Int>()
            currentStateMap[inputString[i]] = i + 1
            states[i] = currentStateMap
        }
    }
}
