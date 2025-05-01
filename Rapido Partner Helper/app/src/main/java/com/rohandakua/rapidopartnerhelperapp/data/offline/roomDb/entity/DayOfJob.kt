package com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Date

@Entity(
    tableName = "day_of_job",
    primaryKeys = ["rapido_partner_id", "day_of_job"]
)
data class DayOfJob(
    @ColumnInfo(name = "rapido_partner_id") val rapido_partner_id: Int,
    @ColumnInfo(name = "day_of_job") val day_of_job: String,  //"dd-MM-yyyy"

    @ColumnInfo(name = "total_distance_covered") val total_distance_covered: Double?,
    @ColumnInfo(name = "total_earnings") val total_earnings: Double?,
    @ColumnInfo(name = "total_time_taken") val total_time_taken: Int?,     // store in minutes as this is the most optimized storage unit for a driver
    @ColumnInfo(name = "total_jobs_completed") val total_jobs_completed: Int?,
    // this are for the targets
    @ColumnInfo(name = "target_distance") val target_distance: Double?,
    @ColumnInfo(name = "target_earnings") val target_earnings: Double?,
    @ColumnInfo(name = "target_time") val target_time: Int?,     // store in minutes as this is the most optimized storage unit for a driver
    @ColumnInfo(name = "target_jobs") val target_jobs: Int?,

    @ColumnInfo(name = "result_of_the_day") val result_of_the_day: Boolean?



)