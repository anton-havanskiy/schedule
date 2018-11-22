package com.ahavansky.schedule.base

import com.ahavansky.schedule.di.component.DaggerPresenterInjector
import com.ahavansky.schedule.di.component.PresenterInjector
import com.ahavansky.schedule.di.module.ContextModule
import com.ahavansky.schedule.di.module.RepositoryModule
import com.ahavansky.schedule.ui.SchedulePresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(RepositoryModule)
        .build()

    init {
        inject()
    }

    open fun onViewCreated() {}
    open fun onViewDestroyed() {}
    open fun refresh() {}

    private fun inject() {
        when (this) {
            is SchedulePresenter -> injector.inject(this)
        }
    }
}