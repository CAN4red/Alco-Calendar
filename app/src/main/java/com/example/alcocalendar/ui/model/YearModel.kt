package com.example.alcocalendar.ui.model

import android.annotation.SuppressLint
import java.time.Month

class YearModel(
    val year: Int,
    val months: List<MonthModel>
) {
    constructor(year: Int) : this(
        year = year,
        months = getAllMonths().map { month ->
            MonthModel(year, month)
        }
    )
}

@SuppressLint("NewApi")
fun getAllMonths(): List<Month> {
    return listOf(
        Month.JANUARY,
        Month.FEBRUARY,
        Month.MARCH,
        Month.APRIL,
        Month.MAY,
        Month.JUNE,
        Month.JULY,
        Month.AUGUST,
        Month.SEPTEMBER,
        Month.OCTOBER,
        Month.NOVEMBER,
        Month.DECEMBER
    )
}