package com.ahavansky.schedule.source

import com.ahavansky.schedule.model.Schedule
import io.reactivex.Flowable

interface ScheduleDataSource {
    val schedules: Flowable<List<Schedule>>
    fun deleteAllSchedules()
    fun saveSchedules(vararg schedule: Schedule)
}