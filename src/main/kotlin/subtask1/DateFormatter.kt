package subtask1

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFormatter {

    private val months =
        mapOf(
            Pair("1", "января"),
            Pair("2", "февраля"),
            Pair("3", "марта"),
            Pair("4", "апреля"),
            Pair("5", "мая"),
            Pair("6", "июня"),
            Pair("7", "июля"),
            Pair("8", "августа"),
            Pair("9", "сентября"),
            Pair("10", "октября"),
            Pair("11", "ноября"),
            Pair("12", "декабря")
        )

    private val weekDays =
        mapOf(
            Pair(1, "понедельник"),
            Pair(2, "вторник"),
            Pair(3, "среда"),
            Pair(4, "четверг"),
            Pair(5, "пятница"),
            Pair(6, "суббота"),
            Pair(7, "воскресенье")
        )

    private val wrongDate = "Такого дня не существует"

    fun toTextDay(day: String, month: String, year: String): String {

        val textMonth = months[month] ?: return wrongDate

        val date = LocalDate.parse("$year.$month.$day",
            DateTimeFormatter.ofPattern("yyyy.M.d"))
        if (!date.isLeapYear && month == "2" && day == "29") return wrongDate

        return "$day $textMonth, ${weekDays[date.dayOfWeek.value]}"
    }
}
