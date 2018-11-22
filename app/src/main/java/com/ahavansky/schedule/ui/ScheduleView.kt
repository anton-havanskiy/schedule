package com.ahavansky.schedule.ui

import android.support.annotation.StringRes
import com.ahavansky.schedule.base.BaseView
import com.ahavansky.schedule.model.Schedule

interface ScheduleView : BaseView {
    fun updateSchedules(schedules: List<Schedule>)
    fun showError(error: String)
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    fun showLoading()
    fun hideLoading()
}