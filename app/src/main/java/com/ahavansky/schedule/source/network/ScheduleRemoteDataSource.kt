package com.ahavansky.schedule.source.network

import com.ahavansky.schedule.model.Schedule
import com.ahavansky.schedule.source.ScheduleDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class ScheduleRemoteDataSource @Inject constructor(val scheduleApi: ScheduleApi): ScheduleDataSource {

    override val schedules: Flowable<List<Schedule>>
        get() {
            return scheduleApi.getSchedules()
        }

    override fun deleteAllSchedules() {
        // Not required
    }

    override fun saveSchedules(vararg schedule: Schedule) {
        // Not required
    }
}