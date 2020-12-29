package com.example.buildflavorsexample

import java.text.SimpleDateFormat
import java.util.Date

fun getCurrentDate() = SimpleDateFormat("H:mm a").format(Date())