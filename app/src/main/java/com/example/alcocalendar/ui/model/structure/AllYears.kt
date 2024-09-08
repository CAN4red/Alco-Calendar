package com.example.alcocalendar.ui.model.structure

import android.util.Log
import com.example.alcocalendar.ui.model.MonthModel
import com.example.alcocalendar.ui.model.YearModel

object AllYears : Iterable<MonthModel> {

    private var lastYear: Int = 2100

    private val years: MutableMap<Int, YearModel> =
        (2000..lastYear).toList().associateWith { year ->
            YearModel(year)
        }.toMutableMap()

    fun getMonths(): List<MonthModel> {
        val monthList: MutableList<MonthModel> = mutableListOf()

        years.values.forEach { year ->
            year.months.values.forEach { month ->
                monthList.add(month)
            }
        }
        return monthList
    }

    // Part of attempt to make a scalable list of all years
    fun addYear(): AllYears {
        lastYear++
        years[lastYear] = YearModel(lastYear)
        Log.d("lol", "year added")
        return this
    }

    override fun iterator(): MonthIterator {
        return MonthIterator
    }
}