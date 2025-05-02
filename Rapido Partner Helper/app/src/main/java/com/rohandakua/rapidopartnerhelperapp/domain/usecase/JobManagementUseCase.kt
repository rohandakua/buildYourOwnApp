package com.rohandakua.rapidopartnerhelperapp.domain.usecase

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository
import kotlinx.coroutines.flow.Flow

class JobManagementUseCase(
    private val jobRepository: DayOfJobRepository
) {
    fun getAllJobs(): Flow<MutableList<DayOfJob?>> {
        return jobRepository.getAll()
    }

    suspend fun insertJob(dayOfJob: DayOfJob) {
        jobRepository.insert(dayOfJob)
    }

    suspend fun updateJob(dayOfJob: DayOfJob) {
        jobRepository.update(dayOfJob)
    }

    fun getJobByDate(date: String): Flow<DayOfJob?> {
        return jobRepository.getById(date)
    }
} 