package com.ahavansky.schedule.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.ahavansky.schedule.di.qualifier.DatabaseSource
import com.ahavansky.schedule.di.qualifier.NetworkSource
import com.ahavansky.schedule.source.ScheduleDataSource
import com.ahavansky.schedule.source.ScheduleRepository
import com.ahavansky.schedule.source.database.AppDatabase
import com.ahavansky.schedule.source.database.ScheduleLocalSource
import com.ahavansky.schedule.source.network.ScheduleApi
import com.ahavansky.schedule.source.network.ScheduleRemoteDataSource
import com.ahavansky.schedule.utils.BASE_URL
import com.ahavansky.schedule.utils.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RepositoryModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideScheduleApi(retrofit: Retrofit): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DATA_BASE_NAME
        ).build()
    }

    @Provides
    @Reusable
    @JvmStatic
    @DatabaseSource
    internal fun provideScheduleLocalSource(impl: ScheduleLocalSource): ScheduleLocalSource {
        return impl
    }

    @Provides
    @Reusable
    @JvmStatic
    @NetworkSource
    internal fun provideScheduleRemoteSource(impl: ScheduleRemoteDataSource): ScheduleRemoteDataSource {
        return impl
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideScheduleRepository(
        @NetworkSource scheduleRemoteDataSource: ScheduleRemoteDataSource,
        @DatabaseSource scheduleLocalSource: ScheduleLocalSource
    ): ScheduleDataSource {
        return ScheduleRepository(
            scheduleRemoteDataSource = scheduleRemoteDataSource,
            scheduleLocalDataSource = scheduleLocalSource
        )
    }
}