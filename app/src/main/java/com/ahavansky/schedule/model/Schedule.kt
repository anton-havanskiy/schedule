package com.ahavansky.schedule.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    @ColumnInfo(name = "start_time") var startTime: String,
    @ColumnInfo(name = "end_time") var endTime: String,
    var teacher: String,
    var description: String,
    var place: String,
    @ColumnInfo(name = "week_day") var weekDay: String
)