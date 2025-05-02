package com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.dao.DayOfJobDao
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.Date

class DayOfJobRepositoryImplementation(
    private val dayOfJobDao: DayOfJobDao
) : DayOfJobRepository {
    val TAG = "DayOfJobRepositoryImplementation"
    
    override fun getAll(): Flow<MutableList<DayOfJob?>> = flow {
        try {
            emit(dayOfJobDao.getAllDays().toMutableList())
        } catch (e: Exception) {
            Log.e(TAG, "error getting all days from db ${e.message}")
            emit(mutableListOf())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insert(dayOfJob: DayOfJob) {
        try {
            withContext(Dispatchers.IO) {
                dayOfJobDao.insertDay(dayOfJob)
            }
        } catch (e: Exception) {
            Log.e(TAG, "error in inserting the data in db ${e.message}")
        }
    }

    override suspend fun update(dayOfJob: DayOfJob) {
        try {
            withContext(Dispatchers.IO) {
                dayOfJobDao.updateDay(dayOfJob)
            }
        } catch (e: Exception) {
            Log.e(TAG, "error in updating the data in db ${e.message}")
        }
    }

    override fun getById(date: String): Flow<DayOfJob?> = flow {
        try {
            emit(dayOfJobDao.getDayById(date))
        } catch (e: Exception) {
            Log.e(TAG, "error in getting day by id ${e.message}")
            emit(null)
        }
    }.flowOn(Dispatchers.IO)
}