package com.example.alcocalendar.ui.calendar.month

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcocalendar.ui.model.DrinkingSessionModel
import com.example.alcocalendar.ui.model.structure.CalendarModelAdapter
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("NewApi")
@Composable
fun MonthLayout(
    onClick: (DrinkingSessionModel) -> Unit,
    onTitleClick: () -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    val calendarProvider by remember { mutableStateOf(CalendarModelAdapter) }
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = calendarProvider.getMonthIndex(),
        pageCount = { calendarProvider.monthsCount }
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
                enabled = calendarProvider.hasPreviousMonth(pagerState.currentPage),
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
                    year = calendarProvider.getMonthModel(pagerState.currentPage).year,
                    month = calendarProvider.getMonthModel(pagerState.currentPage).month
                )
                onTitleClick()
            }) {
                Text(
                    text = calendarProvider.getMonthModel(pagerState.currentPage).toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            NavigationButton(
                enabled = calendarProvider.hasNextMonth(pagerState.currentPage),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Forward"
            )
        }

        MonthPager(
            pagerState = pagerState,
            startFromSunday = startFromSunday,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NavigationButton(
    enabled: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String
) {
    IconButton(
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}


@Preview
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun MonthLayoutPreview() {
    MonthLayout(
        onClick = {},
        onTitleClick = {},
        startFromSunday = false
    )
}