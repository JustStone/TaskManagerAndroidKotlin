package com.example.taskmanagerapp

import java.text.DateFormat
import java.time.LocalDate

data class TaskData(
    val taskId: Int,
    val taskStar: Boolean,
    val taskList: String,
    val taskTitle: String,
    val taskInfo: String,
    val taskTime: LocalDate,
    val taskTasks: ArrayList<TaskData>
)
