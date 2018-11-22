package com.ahavansky.schedule.di.component

import com.ahavansky.schedule.base.BaseView
import com.ahavansky.schedule.di.module.ContextModule
import com.ahavansky.schedule.di.module.RepositoryModule
import com.ahavansky.schedule.ui.SchedulePresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, RepositoryModule::class])
interface PresenterInjector {

    fun inject(schedulePresenter: SchedulePresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: RepositoryModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}