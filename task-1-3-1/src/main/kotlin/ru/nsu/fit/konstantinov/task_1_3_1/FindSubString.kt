package ru.nsu.fit.konstantinov.task_1_3_1

import java.io.InputStream

class FindSubString {

    fun findSubStringInFile(stream: InputStream, subString: String): ArrayList<Int> {
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
}
