package com.example.alcocalendar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alcocalendar.ui.calendar.month.MonthLayout
import com.example.alcocalendar.ui.calendar.year.YearLayout
import com.example.alcocalendar.ui.theme.AlcoCalendarTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedScreen by remember { mutableIntStateOf(0) }
            AlcoCalendarTheme {
                Scaffold(bottomBar = {
                    BottomNavigation {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = "Экран 1") },
                            label = { Text("Экран 1") },
                            selected = selectedScreen == 0,
                            onClick = { selectedScreen = 0 }
                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Favorite, contentDescription = "Экран 2") },
                            label = { Text("Экран 2") },
                            selected = selectedScreen == 1,
                            onClick = { selectedScreen = 1 }
                        )
                    }
                }
                ) { innerPadding ->
                    when (selectedScreen) {
                        0 -> MonthLayout(
                            onClick = {},
                            startFromSunday = false,
                            modifier = Modifier.padding(innerPadding)
                        )

                        1 -> YearLayout(
                            startFromSunday = false,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlcoCalendarTheme {
        Greeting("Android")
    }
}