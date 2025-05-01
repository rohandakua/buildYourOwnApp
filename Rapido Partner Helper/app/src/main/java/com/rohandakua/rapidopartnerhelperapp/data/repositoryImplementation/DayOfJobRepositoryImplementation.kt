package com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.dao.DayOfJobDao
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository
import java.util.Date

class DayOfJobRepositoryImplementation(
    private val dayOfJobDao: DayOfJobDao
) : DayOfJobRepository {
    val TAG = "DayOfJobRepositoryImplementation"
    override fun getAll(): MutableList<DayOfJob?> {
        return try {
            dayOfJobDao.getAllDays().toMutableList()
        } catch (e: Exception) {
            Log.e(TAG, "error getting all days from db ${e.message}")
            mutableListOf()
        }
    }

    override fun insert(dayOfJob: DayOfJob) {
        try {
            dayOfJobDao.insertDay(dayOfJob)
        } catch (e: Exception) {
            Log.e(TAG, "error in inserting the data in db ${e.message}")
        }
    }

    override fun update(dayOfJob: DayOfJob) {
        try {
            dayOfJobDao.updateDay(dayOfJob)
        } catch (e: Exception) {
            Log.e(TAG, "error in updating the data in db ${e.message}")
        }
    }

    override fun getById(date: String): DayOfJob? {
        return try {
            dayOfJobDao.getDayById(date)
        } catch (e: Exception) {
            Log.e(TAG, "error in inserting in db ${e.message}")
            null
        }
    }
}