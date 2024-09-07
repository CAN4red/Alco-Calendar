package com.example.alcocalendar.ui.model

import java.time.LocalDate

data class DrinkingSessionModel(
    val date: LocalDate,
    val isDrunk: Boolean = false,
)