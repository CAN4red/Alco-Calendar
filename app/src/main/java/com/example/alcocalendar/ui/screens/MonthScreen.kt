package com.example.alcocalendar.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alcocalendar.ui.calendar.MonthLayout
import com.example.alcocalendar.ui.model.DrinkingSessionModel
import com.example.alcocalendar.ui.model.structure.AllYears
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthPager(
    startFromSunday: Boolean,
    onClick: (DrinkingSessionModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val allYears by remember { mutableStateOf(AllYears) }
    val iterator by remember { mutableStateOf(allYears.iterator()) }

    val pagerState = rememberPagerState(
        initialPage = iterator.getInitialIndex(),
        pageCount = { allYears.getMonths().size }
    )

// Attempt to make a scalable List of Months
//    LaunchedEffect(pagerState.currentPage) {
//            if (pagerState.currentPage == allYears.getMonths().size - 1) {
//                Log.d("lol", "the code reached")
//                allYears.addYear()
//                Log.d("lol", "number of months: ${allYears.getMonths().size}")
//                pagerState.scrollToPage(pagerState.currentPage)
//            }
//    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { page ->

        MonthLayout(
            monthModel = allYears.getMonths()[page],
            onClick = onClick,
            startFromSunday = startFromSunday,
            modifier = Modifier.fillMaxSize()
        )
        Log.d("lol", "number of months in the Pager: ${allYears.getMonths().size}")
    }

    Text(text = "Lol: ${pagerState.pageCount}")
}

@Preview
@Composable
fun MonthPagerPreview() {
    MonthPager(startFromSunday = false, onClick = {})
}