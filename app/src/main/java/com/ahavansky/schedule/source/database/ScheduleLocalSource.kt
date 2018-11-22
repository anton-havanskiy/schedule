package com.ahavansky.schedule.source.database

import com.ahavansky.schedule.model.Schedule
import com.ahavansky.schedule.source.ScheduleDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleLocalSource @Inject constructor(val appDatabase: AppDatabase) : ScheduleDataSource {

    override val schedules: Flowable<List<Schedule>>
        get() {
            return appDatabase.scheduleDao().getSchedules()
        }

    override fun deleteAllSchedules() {
        appDatabase.scheduleDao().clearTable()
    }

    override fun saveSchedules(vararg schedule: Schedule) {
        appDatabase.scheduleDao().saveAll(*schedule)
    }
}