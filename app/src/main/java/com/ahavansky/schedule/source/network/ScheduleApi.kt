package com.ahavansky.schedule.source.network

import com.ahavansky.schedule.model.Schedule
import com.ahavansky.schedule.utils.ALL_SCHEDULES
import io.reactivex.Flowable
import retrofit2.http.GET

interface ScheduleApi {
    @GET(ALL_SCHEDULES)
    fun getSchedules(): Flowable<List<Schedule>>
}