package com.ahavansky.schedule.source.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ahavansky.schedule.model.Schedule
import io.reactivex.Flowable

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getSchedules(): Flowable<List<Schedule>>

    @Query("DELETE FROM Schedule")
    fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg schedule: Schedule)
}
