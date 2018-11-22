package com.ahavansky.schedule.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ahavansky.schedule.R
import com.ahavansky.schedule.databinding.ItemScheduleBinding
import com.ahavansky.schedule.model.Schedule
import com.ahavansky.schedule.model.WeekDay

class ScheduleAdapter(private val context: Context) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    private var schedules: List<Schedule> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemScheduleBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    fun updatePosts(posts: List<Schedule>) {
        this.schedules = posts
        notifyDataSetChanged()
    }

    class ScheduleViewHolder(private val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: Schedule) {
            binding.schedule = schedule
            binding.weekDayEnum = WeekDay.NONE
            binding.executePendingBindings()
        }
    }
}