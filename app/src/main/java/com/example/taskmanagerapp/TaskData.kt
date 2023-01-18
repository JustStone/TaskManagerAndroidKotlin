package com.example.taskmanagerapp

import java.io.Serializable
import java.text.DateFormat
import java.time.LocalDate

data class TaskData(
    val taskStar: Boolean,
    val taskList: String,
    val taskTitle: String,
    val taskInfo: String,
    val taskTime: String,
) : Serializable
