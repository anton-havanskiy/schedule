package com.ahavansky.schedule.source

import android.annotation.SuppressLint
import com.ahavansky.schedule.model.Schedule
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleDataSource,
    private val scheduleLocalDataSource: ScheduleDataSource
) : ScheduleDataSource {
    private val schedulesRelay: BehaviorRelay<List<Schedule>> = BehaviorRelay.create()

    override val schedules: Flowable<List<Schedule>>
        @SuppressLint("RxLeakedSubscription")
        get() {
            return schedulesRelay.toFlowable(BackpressureStrategy.LATEST)
                .doOnSubscribe {
                    scheduleRemoteDataSource
                        .schedules
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            { schedulesList ->
                                deleteAllSchedules()
                                saveSchedules(*schedulesList.toTypedArray())
                                schedulesRelay.accept(schedulesList)
                            },
                            { error ->
                                scheduleLocalDataSource
                                    .schedules
                                    .subscribe { schedulesLocalList ->
                                        schedulesRelay.accept(schedulesLocalList)
                                    }
                            }
                        )
                }
        }

    override fun deleteAllSchedules() {
        scheduleLocalDataSource.deleteAllSchedules()
    }

    override fun saveSchedules(vararg schedule: Schedule) {
        scheduleLocalDataSource.saveSchedules(*schedule)
    }
}