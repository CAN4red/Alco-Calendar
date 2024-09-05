package com.example.alcocalendar.ui.calendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcocalendar.ui.model.MonthModel
import java.time.LocalDate
import java.time.Month

@SuppressLint("NewApi")
@Composable
fun MonthLayout(
    monthModel: MonthModel,
    onClick: (LocalDate) -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "${monthModel.name} ${monthModel.year}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Forward"
                )
            }
        }

        MonthGrid(
            monthModel = monthModel,
            onClick = onClick,
            startFromSunday = startFromSunday,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun MonthLayoutPreview() {
    MonthLayout(
        monthModel = MonthModel(2024, Month.AUGUST),
        onClick = {},
        startFromSunday = false
    )
}