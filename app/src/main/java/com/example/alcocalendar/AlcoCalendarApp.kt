package com.example.alcocalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alcocalendar.ui.calendar.month.MonthLayout
import com.example.alcocalendar.ui.calendar.year.YearLayout
import com.example.alcocalendar.ui.navigation.CalendarScreen

@Composable
fun AlcoCalendarApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = CalendarScreen.MonthView.name,
        modifier = modifier
    ) {
        composable(route = CalendarScreen.MonthView.name) {
            MonthLayout(
                onClick = {},
                onTitleClick = { navController.navigate(CalendarScreen.YearView.name) },
                startFromSunday = false,
                modifier = modifier
            )
        }
        composable(route = CalendarScreen.YearView.name) {
            YearLayout(
                startFromSunday = false,
                onMonthClick = { navController.navigate(CalendarScreen.MonthView.name) },
                onTitleClick = { navController.navigate(CalendarScreen.MonthView.name) },
                modifier = modifier,
            )
        }
    }
}
