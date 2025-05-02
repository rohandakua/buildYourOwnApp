package com.rohandakua.rapidopartnerhelperapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.getCurrentDate
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.getGreetings
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.getRideMessage
import com.rohandakua.rapidopartnerhelperapp.domain.model.DayOfJobUiModel
import com.rohandakua.rapidopartnerhelperapp.domain.model.RapidoPartnerUiModel
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.JobManagementUseCase
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.SettingsUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeScreenViewModel(
    private val jobManagementUseCase: JobManagementUseCase,
    private val partnerId: Long = 1
) : ViewModel() {
    val TAG = "HomeScreenViewModel"
    private val _currentJob = MutableLiveData<DayOfJobUiModel>()
    val currentJob: LiveData<DayOfJobUiModel> = _currentJob
    private val _partnerName = MutableLiveData<String>("Rapido Partner")
    val partnerName: LiveData<String> = _partnerName

    private val _partnerRating = MutableLiveData<Double>(4.5)
    val partnerRating: LiveData<Double> = _partnerRating

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _greatingMessage = MutableLiveData<String?>(null)
    val greatingMessage: LiveData<String?> = _greatingMessage

    private val _rideMessage = MutableLiveData<String?>(null)
    val rideMessage: LiveData<String?> = _rideMessage

    private val _isFirstVisit = MutableLiveData<Boolean>(false) // this is for making the user store the targets of the day
    val isFirstVisit: LiveData<Boolean> = _isFirstVisit

    private val _targetDistance = MutableLiveData<Double>(0.0)
    val targetDistance: LiveData<Double> = _targetDistance
    private val _targetTime = MutableLiveData<Int>(0)
    val targetTime: LiveData<Int> = _targetTime
    private val _targetEarnings = MutableLiveData<Double>(0.0)
    val targetEarnings: LiveData<Double> = _targetEarnings
    private val _targetJobs = MutableLiveData<Int>(0)
    val targetJobs: LiveData<Int> = _targetJobs

    init {
        loadCurrentJob()
        _greatingMessage.value = getGreetings(_partnerName.value.toString())
        _rideMessage.value = getRideMessage(_currentJob.value!!.totalTimeTaken?: 0)
    }

    fun loadCurrentJob() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentDate = getCurrentDate()
                var job = jobManagementUseCase.getJobByDate(currentDate)

                if (job == null) {
                    // viewer is opening for the first time
                    _isFirstVisit.value = true

                    job = DayOfJobUiModel.toEntity(DayOfJobUiModel(
                        rapidoPartnerId = partnerId.toInt(),
                        dayOfJob = currentDate,
                        totalDistanceCovered = 0.0,
                        totalEarnings = 0.0,
                        totalTimeTaken = 0,
                        totalJobsCompleted = 0,
                        targetDistance = 0.0,
                        targetEarnings = 0.0,
                        targetTime = 0,
                        targetJobs = 0,
                        resultOfTheDay = null
                    ))
                    jobManagementUseCase.insertJob(job)
                }

                _currentJob.value = DayOfJobUiModel.fromEntity(job)

            } catch (e: Exception) {
                Log.e(TAG , "error in loading job data: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun setDailyTargets(targetDistance: Double, targetTime: Int, targetEarnings: Double, targetJobs: Int) {
        viewModelScope.launch {
            try {
                _currentJob.value?.let { currentJob ->
                    val updatedJob = currentJob.copy(
                        targetDistance = targetDistance,
                        targetTime = targetTime,
                        targetEarnings = targetEarnings,
                        targetJobs = targetJobs
                    )

                    jobManagementUseCase.updateJob(DayOfJobUiModel.toEntity(updatedJob))
                    _currentJob.value = updatedJob
                    _isFirstVisit.value = false
                }
            } catch (e: Exception) {
                Log.e(TAG , "error in setting daily targets: ${e.message}")
            }
        }
    }

    fun addRide(distance: Double, time: Int, fare: Double) {
        viewModelScope.launch {
            try {
                _currentJob.value?.let { currentJob ->
                    // Update job with new ride information
                    val newTotalDistance = (currentJob.totalDistanceCovered ?: 0.0) + distance
                    val newTotalTime = (currentJob.totalTimeTaken ?: 0) + time
                    val newTotalEarnings = (currentJob.totalEarnings ?: 0.0) + fare
                    val newTotalJobs = (currentJob.totalJobsCompleted ?: 0) + 1

                    val targetsMet = checkIfTargetsMet(
                        newTotalDistance,
                        newTotalTime,
                        newTotalEarnings,
                        newTotalJobs,
                        currentJob
                    )

                    val updatedJob = currentJob.copy(
                        totalDistanceCovered = newTotalDistance,
                        totalTimeTaken = newTotalTime,
                        totalEarnings = newTotalEarnings,
                        totalJobsCompleted = newTotalJobs,
                        resultOfTheDay = targetsMet
                    )

                    jobManagementUseCase.updateJob(DayOfJobUiModel.toEntity(updatedJob))
                    _currentJob.value = updatedJob
                    _rideMessage.value = getRideMessage(newTotalTime)
                }
            } catch (e: Exception) {

                Log.e(TAG , "error in adding ride data: ${e.message}")
            }
        }
    }
    private fun checkIfTargetsMet(
        distance: Double,
        time: Int,
        earnings: Double,
        jobs: Int,
        currentJob: DayOfJobUiModel
    ): Boolean {
        return (currentJob.targetDistance == null || distance >= currentJob.targetDistance) &&
                (currentJob.targetTime == null || time >= currentJob.targetTime) &&
                (currentJob.targetEarnings == null || earnings >= currentJob.targetEarnings) &&
                (currentJob.targetJobs == null || jobs >= currentJob.targetJobs)
    }

    fun endDay() {
        viewModelScope.launch {
            try {
                _currentJob.value?.let { currentJob ->
                    // Check if targets are met
                    val targetsMet = checkIfTargetsMet(
                        currentJob.totalDistanceCovered ?: 0.0,
                        currentJob.totalTimeTaken ?: 0,
                        currentJob.totalEarnings ?: 0.0,
                        currentJob.totalJobsCompleted ?: 0,
                        currentJob
                    )

                    val updatedJob = currentJob.copy(
                        resultOfTheDay = targetsMet
                    )

                    jobManagementUseCase.updateJob(DayOfJobUiModel.toEntity(updatedJob))
                    _currentJob.value = updatedJob
                }
            } catch (e: Exception) {
                Log.e(TAG , "error in ending day: ${e.message}")
            }
        }
    }

}