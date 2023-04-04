package com.jydev.mindtravelapplication.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd")
val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun LocalDateTime.isToday() : Boolean{
    val cur = LocalDateTime.now()
    return cur.year == this.year && cur.month == this.month && cur.dayOfMonth == this.dayOfMonth
}