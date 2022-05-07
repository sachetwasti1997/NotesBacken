package com.sachet.notesapp.errors

import java.sql.Timestamp
import java.time.LocalDateTime

data class ApiError(
    val message: String ?= null,
    val code: Int ?= null,
    val timeStamp: Timestamp = Timestamp.valueOf(LocalDateTime.now())
)