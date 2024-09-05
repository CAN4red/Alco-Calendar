package com.example.alcocalendar.ui.model

import android.annotation.SuppressLint
import com.example.alcocalendar.ui.calendar.getWeekday
import kotlinx.collections.immutable.toImmutableList
import java.time.LocalDate
import java.time.Month

class MonthModel(
    val name: String,
    val year: Int,
    val dates: List<LocalDate>,
    val firstDay: LocalDate,
    val lastDay: LocalDate,
) {
    @SuppressLint("NewApi")
    constructor(year: Int, month: Month) : this(
        name = month.getMonthName(),
        year = year,
        dates = generateDates(year, month),
        firstDay = getFirstDay(year, month),
        lastDay = getLastDay(year, month),
    )

    @SuppressLint("NewApi")
    fun getMonthMatrix(
        startFromSunday: Boolean
    ): List<List<LocalDate>> {
        val weekdaysSpread: MutableList<MutableList<LocalDate>> = MutableList(7) { mutableListOf() }

        for (date in this.dates) {
            val weekdayString = date.getWeekday()
            val weekday = Weekdays.fromString(weekdayString)
            weekdaysSpread[weekday.ordinal].add(date)
        }
        if (startFromSunday) {
            weekdaysSpread.add(0, weekdaysSpread.removeAt(6))
        }
        return weekdaysSpread.toImmutableList().map { it.toImmutableList() }
    }
}

@SuppressLint("NewApi")
fun getLastDay(year: Int, month: Month): LocalDate {
    val isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    return LocalDate.of(year, month, month.length(isLeap))
}

@SuppressLint("NewApi")
fun getFirstDay(year: Int, month: Month): LocalDate =
    LocalDate.of(year, month, 1)

@SuppressLint("NewApi")
fun generateDates(year: Int, month: Month): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    val lastDay = getLastDay(year, month).dayOfMonth
    for (day in 1..lastDay) {
        dates.add(LocalDate.of(year, month, day))
    }
    return dates.toImmutableList()
}

@SuppressLint("NewApi")
fun Month.getMonthName(): String {
    return when (this) {
        Month.JANUARY -> "January"
        Month.FEBRUARY -> "February"
        Month.MARCH -> "March"
        Month.APRIL -> "April"
        Month.MAY -> "May"
        Month.JUNE -> "June"
        Month.JULY -> "July"
        Month.AUGUST -> "August"
        Month.SEPTEMBER -> "September"
        Month.OCTOBER -> "October"
        Month.NOVEMBER -> "November"
        Month.DECEMBER -> "December"
    }
}
