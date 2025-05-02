package com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import kotlinx.coroutines.flow.Flow

interface DayOfJobRepository {
    fun getAll(): Flow<MutableList<DayOfJob?>>
    suspend fun insert(dayOfJob: DayOfJob)
    suspend fun update(dayOfJob: DayOfJob)
    fun getById(date: String): Flow<DayOfJob?>
}