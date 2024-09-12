package com.example.alcocalendar.ui.model.structure

import android.annotation.SuppressLint
import android.util.Log
import com.example.alcocalendar.ui.model.MonthModel
import com.example.alcocalendar.ui.model.YearModel
import java.time.LocalDate
import java.time.Month

@SuppressLint("NewApi")
object CalendarModel {

    private const val FIRST_YEAR: Int = 2000
    private const val LAST_YEAR: Int = 2200
    private const val MONTHS_NUMBER: Int = 12

    val initialYear: Int = LocalDate.now().year
    val initialMonth: Month = LocalDate.now().month

    val yearsCount = (FIRST_YEAR..LAST_YEAR).toList().size
    val monthsCount = yearsCount * MONTHS_NUMBER

    private val calendarStructure: MutableMap<Int, YearModel> =
        (FIRST_YEAR..LAST_YEAR).toList().associateWith { year ->
            YearModel(year)
        }.toMutableMap()

    fun getMonthModel(year: Int, month: Month): MonthModel {
        return getYearModel(year).getMonthModel(month)
    }

    fun getYearModel(year: Int): YearModel {
        return calendarStructure[year] ?: throw IllegalArgumentException("Invalid year: $year")
    }

    fun hasNextMonth(year: Int, month: Month): Boolean {
        return !(year == LAST_YEAR && month == Month.DECEMBER)
    }

    fun hasPreviousMonth(year: Int, month: Month): Boolean {
        return !(year == FIRST_YEAR && month == Month.JANUARY)
    }

    fun hasNextYear(year: Int): Boolean {
        return year != LAST_YEAR
    }

    fun hasPreviousYear(year: Int): Boolean {
        return year != FIRST_YEAR
    }
}