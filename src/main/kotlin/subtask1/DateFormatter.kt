package subtask1

import java.lang.NumberFormatException

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
            Pair(0, "воскресенье")
        )

    private val wrongDate = "Такого дня не существует"

    fun toTextDay(day: String, month: String, year: String): String {

        val textMonth = months[month] ?: return wrongDate
        val dayNumber: Int
        val monthNumber: Int
        val yearNumber: Int
        val weekDay: String

        try {
            dayNumber = day.toInt()
            monthNumber = month.toInt()
            yearNumber = year.toInt()
        } catch (e: NumberFormatException) {
            return wrongDate
        }

        if (isDateValid(dayNumber, monthNumber, yearNumber)) {
            weekDay = getDayOfWeek(dayNumber, monthNumber, yearNumber)
        } else return wrongDate

        return "$day ${textMonth}, $weekDay"
    }

    private fun isDateValid(day: Int, month: Int, year: Int): Boolean {
        when {
            day <= 0 || month <= 0 || month > 12 || year <= 0 -> return false
            month == 2 && year % 4 == 0 -> return day <= 29
            month == 2 -> return day <= 28
            month <= 7 && month % 2 == 1 -> return day <= 31
            month <= 6 && month % 2 == 0 -> return day <= 30
            month % 2 == 0 -> return day <= 31
            month % 2 == 1 -> return day <= 30
        }
        return false
    }

    private fun getDayOfWeek(day: Int, month: Int, year: Int): String {

        val daysPassed =
            (if (year % 4 == 0 && month > 2) 1 else 0) + // add 29th february
                    when (month) {
                        2 -> 31 + day
                        3 -> 31 + 28 + day
                        4 -> 31 + 28 + 31 + day
                        5 -> 31 + 28 + 31 + 30 + day
                        6 -> 31 + 28 + 31 + 30 + 31 + day
                        7 -> 31 + 28 + 31 + 30 + 31 + 30 + day
                        8 -> 31 + 28 + 31 + 30 + 31 + 30 + 31 + day
                        9 -> 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + day
                        10 -> 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + day
                        11 -> 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + day
                        12 -> 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + day
                        else -> day // if january
                    } +
                    year * 365 +
                    when {
                        year % 4 != 0 -> year / 4 + 1
                        else -> year / 4
                    }
        return weekDays[(daysPassed - 3) % 7] ?: wrongDate
    }
}
