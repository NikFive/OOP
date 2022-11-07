package ru.nsu.fit.konstantinov.task_1_3_1

class FindSubString {
    fun kmpAlgorithm(subString: String, string: String): ArrayList<Int> {
        val result = arrayListOf<Int>()
        val subStringLength = subString.length
        val stringLength = string.length

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
                result.add(i - j)
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
