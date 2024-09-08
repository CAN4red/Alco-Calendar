package com.example.alcocalendar.ui.model.structure

import android.annotation.SuppressLint
import com.example.alcocalendar.ui.model.MonthModel
//import com.example.alcocalendar.ui.model.structure.allYears
import java.time.LocalDate
import java.time.Month

object MonthIterator : ListIterator<MonthModel> {

    private const val INITIAL_YEAR = 2000
    private const val MONTHS_NUMBER = 12

    @SuppressLint("NewApi")
    private var monthPointer = getPointer(LocalDate.now().year, LocalDate.now().month)

    private fun getPointer(year: Int, month: Month): Int {
        return (year - INITIAL_YEAR) * MONTHS_NUMBER + month.ordinal
    }

    @SuppressLint("NewApi")
    fun getInitialIndex(): Int {
        return getPointer(LocalDate.now().year, LocalDate.now().month)
    }

    override fun hasNext(): Boolean {
        return monthPointer != AllYears.getMonths().size - 1
    }

    override fun hasPrevious(): Boolean {
        return monthPointer != 0
    }

    override fun next(): MonthModel {
        if (!hasNext()) {
            throw NoSuchElementException("There is no next element")
        }
        monthPointer++
        return AllYears.getMonths()[monthPointer]
    }

    override fun nextIndex(): Int {
        if (!hasNext()) {
            return AllYears.getMonths().size
        }
        return monthPointer + 1
    }

    override fun previous(): MonthModel {
        if (!hasPrevious()) {
            throw NoSuchElementException("There is no previous element")
        }
        monthPointer--
        return AllYears.getMonths()[monthPointer]
    }

    override fun previousIndex(): Int {
        if (!hasPrevious()) {
            return AllYears.getMonths().size
        }
        return monthPointer - 1
    }
}
