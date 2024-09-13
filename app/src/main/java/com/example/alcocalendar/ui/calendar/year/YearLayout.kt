package com.example.alcocalendar.ui.calendar.year

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcocalendar.ui.calendar.month.NavigationButton
import com.example.alcocalendar.ui.model.structure.CalendarModelAdapter
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("NewApi")
@Composable
fun YearLayout(
    onMonthClick: () -> Unit,
    onTitleClick: () -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier
) {
    val calendarProvider by remember { mutableStateOf(CalendarModelAdapter) }
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = calendarProvider.getYearIndex(),
        pageCount = { calendarProvider.yearsCount }
    )

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            NavigationButton(
                enabled = calendarProvider.hasPreviousYear(pagerState.currentPage),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back"
            )

            TextButton(onClick = {
                CalendarModelAdapter.updateCalendarState(
                    year = calendarProvider.getYearModel(
                        pagerState.currentPage
                    ).year
                )
                onTitleClick()
            }) {
                Text(
                    text = calendarProvider.getYearModel(pagerState.currentPage).year.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            NavigationButton(
                enabled = calendarProvider.hasNextYear(pagerState.currentPage),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Forward"
            )
        }

        YearPager(
            pagerState = pagerState,
            onMonthClick = onMonthClick,
            startFromSunday = startFromSunday,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@SuppressLint("NewApi")
@Composable
@Preview
fun YearLayoutPreview() {
    YearLayout(
        onMonthClick = {},
        onTitleClick = {},
        startFromSunday = false
    )
}