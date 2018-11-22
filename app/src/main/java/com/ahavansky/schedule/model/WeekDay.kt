package com.ahavansky.schedule.model

enum class WeekDay(val weekDayString: String) {
    NONE("Неопределен"),
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    fun of(dayOfWeek: String): WeekDay {
        return when (dayOfWeek) {
            "1" -> MONDAY
            "2" -> TUESDAY
            "3" -> WEDNESDAY
            "4" -> THURSDAY
            "5" -> FRIDAY
            "6" -> SATURDAY
            "7" -> SUNDAY
            else -> NONE
        }
    }

}