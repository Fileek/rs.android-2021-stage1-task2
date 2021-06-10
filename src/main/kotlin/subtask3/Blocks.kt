package subtask3

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

class Blocks {
    fun getData(blockA: Array<*>, blockB: KClass<*>): Any {
        when (blockB) {
            String::class -> {
                var str = ""
                for (element in blockA)
                    if (element == element as? String) str += element
                return str
            }
            Int::class -> {
                var sum = 0
                for (element in blockA)
                    if (element == element as? Int) sum += element as Int
                return sum
            }
            LocalDate::class -> {
                var date: LocalDate = LocalDate.parse("1970-01-01")
                for (element in blockA) {
                    if (element == element as? LocalDate)
                        if (element as LocalDate > date) date = element
                }
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                return date.format(formatter)
            }
            else -> return "null"
        }
    }
}
