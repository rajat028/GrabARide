package com.grabaride.utils

import android.text.TextUtils
import android.util.Patterns
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val TAG = "GRAB_A_RIDE"

fun CharSequence?.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = this.length in 6..15

fun Long.convertMillisToDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    val instant = Instant.ofEpochMilli(this)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date)
}

fun Long.convertMillisToTime(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm");
    val instant = Instant.ofEpochMilli(this)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date)
}

