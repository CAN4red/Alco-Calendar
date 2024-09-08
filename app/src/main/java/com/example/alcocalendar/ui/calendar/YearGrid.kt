package com.example.alcocalendar.ui.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alcocalendar.ui.model.MonthModel
import com.example.alcocalendar.ui.model.YearModel
import java.time.Month

@Composable
fun YearGrid(
    year: Int,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    val yearModel = YearModel(year)

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(items = yearModel.months.values.toList()) { month ->
            NonDetailedMonthLayout(
                monthModel = month,
                startFromSunday = startFromSunday,
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 16.dp,
                    start = 8.dp,
                    end = 8.dp
                )
            )
        }
    }
}


@SuppressLint("NewApi")
@Composable
fun NonDetailedMonthLayout(
    monthModel: MonthModel,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = monthModel.name)

        NonDetailedMonthGrid(
            monthModel = monthModel,
            startFromSunday = startFromSunday,
            modifier = modifier,
        )
    }
}


@SuppressLint("NewApi")
@Composable
fun NonDetailedMonthGrid(
    monthModel: MonthModel,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    val monthMatrix = monthModel.getMonthMatrix(startFromSunday = startFromSunday)

    Row(modifier = modifier.fillMaxWidth()) {
        monthMatrix.forEach { sessions ->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.weight(1f)
            ) {
                // Adding empty cells at the start of the month
                if (monthMatrix.indexOf(sessions) + 1 < sessions[0].date.dayOfMonth &&
                    sessions[0].date.dayOfMonth != 1
                ) {
                    EmptyCell(modifier = Modifier.fillMaxWidth())
                }

                // Generating the dates
                sessions.forEach { session ->
                    SmallDateCell(
                        session = session,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Adding empty cells at the end of the month
                if (monthMatrix[0].last().date.dayOfMonth - sessions.last().date.dayOfMonth >= 1) {
                    EmptyCell(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
@Preview
fun YearGridPreview() {
    YearGrid(year = 2024, startFromSunday = false)
}

@SuppressLint("NewApi")
@Composable
@Preview
fun NonDetailedMonthGridPreview() {
    NonDetailedMonthGrid(MonthModel(2024, Month.AUGUST), startFromSunday = false)
}
