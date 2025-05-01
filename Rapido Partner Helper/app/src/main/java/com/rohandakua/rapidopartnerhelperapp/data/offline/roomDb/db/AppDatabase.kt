package com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.dao.DayOfJobDao
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob

@Database(entities = [DayOfJob::class] , version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dayOfJobDao() : DayOfJobDao
}