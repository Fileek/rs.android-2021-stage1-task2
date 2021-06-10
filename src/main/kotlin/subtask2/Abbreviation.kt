package subtask2

class Abbreviation {

    fun abbreviationFromA(a: String, b: String): String {
        val upA = a.toUpperCase()
        var resultStr = ""
        var j = 0

        for (i in b.indices) {
            while (j < a.length) {
                if (upA[j] == b[i]) {
                    resultStr += upA[j]
                    break
                }
                else j++
            }
        }
        return if (resultStr == b) "YES" else "NO"
    }
}
