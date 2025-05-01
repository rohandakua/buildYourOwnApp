package com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface DayOfJobRepository {
    fun getAll(): MutableList<DayOfJob?>
    fun insert(dayOfJob: DayOfJob)
    fun update(dayOfJob: DayOfJob)
    fun getById(date: String): DayOfJob?

}