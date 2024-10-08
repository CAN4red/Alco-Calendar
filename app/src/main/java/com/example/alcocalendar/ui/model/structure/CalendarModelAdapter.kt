package com.example.alcocalendar.ui.model.structure

import android.annotation.SuppressLint
import android.util.Log
import com.example.alcocalendar.ui.model.MonthModel
import com.example.alcocalendar.ui.model.YearModel
import com.example.alcocalendar.ui.model.structure.CalendarModel.currentMonth
import com.example.alcocalendar.ui.model.structure.CalendarModel.currentYear
import java.time.Month

object CalendarModelAdapter : CalendarProvider {
    private val calendarModel = CalendarModel

    private const val FIRST_YEAR = 2000
    private const val MONTHS_NUMBER = 12

    override val initialYear: Int = calendarModel.initialYear
    override val initialMonth: Month = calendarModel.initialMonth
    override val yearsCount: Int = calendarModel.yearsCount
    override val monthsCount: Int = calendarModel.monthsCount

    fun updateCalendarState(
        year: Int,
        month: Month? = calendarModel.currentMonth
    ) {
        calendarModel.apply {
            currentYear = currentYear ?: initialYear
            currentMonth = currentMonth ?: initialMonth

            currentYear = year
            currentMonth = month
        }
    }

    fun getMonthIndex(): Int {
        return monthToIndex(
            year = currentYear ?: CalendarModel.initialYear,
            month = currentMonth ?: CalendarModel.initialMonth
        )
    }

    fun getYearIndex(): Int {
        return yearToIndex(
            year = currentYear ?: CalendarModel.initialYear
        )
    }

    override fun getMonthModel(index: Int): MonthModel {
        Log.d("lool", index.toString())
        return calendarModel.getMonthModel(
            year = indexToYearForMonth(index),
            month = indexToMonth(index)
        )
    }

    override fun getYearModel(index: Int): YearModel {
        return calendarModel.getYearModel(
            year = indexToYear(index)
        )
    }

    override fun hasNextMonth(index: Int): Boolean {
        return calendarModel.hasNextMonth(
            year = indexToYearForMonth(index),
            month = indexToMonth(index)
        )
    }

    override fun hasPreviousMonth(index: Int): Boolean {
        return calendarModel.hasPreviousMonth(
            year = indexToYearForMonth(index),
            month = indexToMonth(index)
        )
    }

    override fun hasNextYear(index: Int): Boolean {
        return calendarModel.hasNextYear(
            year = indexToYear(index)
        )
    }

    override fun hasPreviousYear(index: Int): Boolean {
        return calendarModel.hasPreviousYear(
            year = indexToYear(index)
        )
    }

    @SuppressLint("NewApi")
    fun indexToMonth(index: Int): Month {
        return Month.of(index % MONTHS_NUMBER + 1)
    }

    fun monthToIndex(year: Int, month: Month): Int {
        return (year - FIRST_YEAR) * MONTHS_NUMBER + month.ordinal
    }

    fun indexToYearForMonth(index: Int): Int {
        return (index / MONTHS_NUMBER) + FIRST_YEAR
    }

    fun indexToYear(index: Int): Int {
        return index + FIRST_YEAR
    }

    fun yearToIndex(year: Int): Int {
        return year - FIRST_YEAR
    }
}
