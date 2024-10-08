package com.example.alcocalendar.ui.calendar.year

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alcocalendar.ui.model.structure.CalendarModelAdapter

@Composable
fun YearPager(
    pagerState: PagerState,
    onMonthClick: () -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier
) {
    val calendarProvider by remember { mutableStateOf(CalendarModelAdapter) }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { yearIndex ->
        val yearModel = remember(yearIndex) { calendarProvider.getYearModel(yearIndex) }
        YearGrid(
            yearModel = yearModel,
            onMonthClick = onMonthClick,
            startFromSunday = startFromSunday,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun YearPagerPreview() {
    val pagerState = rememberPagerState(
        initialPage = CalendarModelAdapter.initialYear,
        pageCount = { CalendarModelAdapter.yearsCount }
    )

    YearPager(pagerState = pagerState, onMonthClick = {}, startFromSunday = false)
}