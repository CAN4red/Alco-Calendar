package com.example.alcocalendar.ui.calendar.month

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alcocalendar.ui.model.DrinkingSessionModel
import com.example.alcocalendar.ui.model.structure.CalendarModelAdapter

@Composable
fun MonthPager(
    pagerState: PagerState,
    startFromSunday: Boolean,
    onClick: (DrinkingSessionModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val calendarProvider by remember { mutableStateOf(CalendarModelAdapter) }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { monthIndex ->
        MonthGrid(
            monthModel = calendarProvider.getMonthModel(monthIndex),
            onClick = onClick,
            startFromSunday = startFromSunday,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Preview
@Composable
fun MonthPagerPreview() {
    val pagerState = rememberPagerState(
        initialPage = CalendarModelAdapter.getMonthIndex(),
        pageCount = { CalendarModelAdapter.monthsCount }
    )
    MonthPager(
        pagerState = pagerState,
        startFromSunday = false,
        onClick = {}
    )
}
