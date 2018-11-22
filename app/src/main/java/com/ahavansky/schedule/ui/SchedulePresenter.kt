package com.ahavansky.schedule.ui

import com.ahavansky.schedule.R
import com.ahavansky.schedule.base.BasePresenter
import com.ahavansky.schedule.source.ScheduleDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SchedulePresenter(scheduleView: ScheduleView) : BasePresenter<ScheduleView>(scheduleView) {
    @Inject
    lateinit var scheduleRepository: ScheduleDataSource

    private var loadSchedulesDisposable: Disposable? = null

    override fun onViewCreated() {
        super.onViewCreated()
        loadSchedules()
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        loadSchedulesDisposable?.dispose()
    }

    override fun refresh() {
        super.refresh()
        loadSchedules()
    }

    private fun loadSchedules() {

        if (loadSchedulesDisposable != null && !loadSchedulesDisposable!!.isDisposed) {
            loadSchedulesDisposable?.dispose()
        }

        view.showLoading()
        loadSchedulesDisposable = scheduleRepository
            .schedules
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEach { view.hideLoading() }
            .subscribe(
                { scheduleList -> view.updateSchedules(scheduleList) },
                { view.showError(R.string.error) }
            )
    }
}