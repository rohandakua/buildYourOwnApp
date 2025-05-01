package com.rohandakua.rapidopartnerhelperapp.domain.model

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.DayOfJob

data class DayOfJobUiModel(
    val rapidoPartnerId: Int,
    val dayOfJob: String,  // "dd-MM-yyyy"
    val totalDistanceCovered: Double?,
    val totalEarnings: Double?,
    val totalTimeTaken: Int?,     // in minutes
    val totalJobsCompleted: Int?,
    val targetDistance: Double?,
    val targetEarnings: Double?,
    val targetTime: Int?,         // in minutes
    val targetJobs: Int?,
    val resultOfTheDay: Boolean?
) {
    companion object {
        fun fromEntity(entity: DayOfJob): DayOfJobUiModel {
            return DayOfJobUiModel(
                rapidoPartnerId = entity.rapido_partner_id,
                dayOfJob = entity.day_of_job,
                totalDistanceCovered = entity.total_distance_covered,
                totalEarnings = entity.total_earnings,
                totalTimeTaken = entity.total_time_taken,
                totalJobsCompleted = entity.total_jobs_completed,
                targetDistance = entity.target_distance,
                targetEarnings = entity.target_earnings,
                targetTime = entity.target_time,
                targetJobs = entity.target_jobs,
                resultOfTheDay = entity.result_of_the_day
            )
        }

        fun toEntity(uiModel: DayOfJobUiModel): DayOfJob {
            return DayOfJob(
                rapido_partner_id = uiModel.rapidoPartnerId,
                day_of_job = uiModel.dayOfJob,
                total_distance_covered = uiModel.totalDistanceCovered,
                total_earnings = uiModel.totalEarnings,
                total_time_taken = uiModel.totalTimeTaken,
                total_jobs_completed = uiModel.totalJobsCompleted,
                target_distance = uiModel.targetDistance,
                target_earnings = uiModel.targetEarnings,
                target_time = uiModel.targetTime,
                target_jobs = uiModel.targetJobs,
                result_of_the_day = uiModel.resultOfTheDay
            )
        }
    }
}
