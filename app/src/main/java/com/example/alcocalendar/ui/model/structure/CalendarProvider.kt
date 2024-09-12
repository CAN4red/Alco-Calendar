package com.example.alcocalendar.ui.model.structure

import com.example.alcocalendar.ui.model.MonthModel
import com.example.alcocalendar.ui.model.YearModel
import java.time.Month

interface CalendarProvider {
    val initialYear: Int
    val initialMonth: Month
    val yearsCount: Int
    val monthsCount: Int

    fun getMonthModel(index: Int): MonthModel

    fun getYearModel(index: Int): YearModel

    fun hasNextMonth(index: Int): Boolean

    fun hasPreviousMonth(index: Int): Boolean

    fun hasNextYear(index: Int): Boolean

    fun hasPreviousYear(index: Int): Boolean
}