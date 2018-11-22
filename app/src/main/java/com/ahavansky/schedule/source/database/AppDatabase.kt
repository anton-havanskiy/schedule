package com.ahavansky.schedule.source.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ahavansky.schedule.model.Schedule

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}