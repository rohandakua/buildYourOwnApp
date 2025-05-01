package com.rohandakua.rapidopartnerhelperapp.domain.usecase

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository

class JobManagementUseCase(
    private val jobRepository: DayOfJobRepository
) {
    fun getAllJobs(): MutableList<DayOfJob?> {
        return jobRepository.getAll()
    }

    fun insertJob(dayOfJob: DayOfJob) {
        jobRepository.insert(dayOfJob)
    }

    fun updateJob(dayOfJob: DayOfJob) {
        jobRepository.update(dayOfJob)
    }

    fun getJobByDate(date: String): DayOfJob? {
        return jobRepository.getById(date)
    }
} 