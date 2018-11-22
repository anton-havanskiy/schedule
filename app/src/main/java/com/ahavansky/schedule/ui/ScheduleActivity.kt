package com.ahavansky.schedule.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.ahavansky.schedule.R
import com.ahavansky.schedule.base.BaseActivity
import com.ahavansky.schedule.databinding.ActivityScheduleBinding
import com.ahavansky.schedule.model.Schedule
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : BaseActivity<SchedulePresenter>(), ScheduleView {

    private lateinit var binding: ActivityScheduleBinding
    private val scheduleAdapter: ScheduleAdapter = ScheduleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule)
        binding.adapter = scheduleAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        setOnRefreshListener()
        showRefreshTip()

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun instantiatePresenter(): SchedulePresenter {
        return SchedulePresenter(this)
    }

    override fun updateSchedules(schedules: List<Schedule>) {
        scheduleAdapter.updatePosts(schedules)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        schedule_swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoading() {
        schedule_swipe_refresh_layout.isRefreshing = false
    }

    private fun setOnRefreshListener() {
        schedule_swipe_refresh_layout.setOnRefreshListener {
            presenter.refresh()
        }
    }

    private fun showRefreshTip() {
        Toast.makeText(this, "Swipe to refresh", Toast.LENGTH_LONG).show()
    }
}