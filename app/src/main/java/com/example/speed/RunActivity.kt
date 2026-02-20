package com.example.speed

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RunActivity : ViewModel() {

    private val _time = MutableStateFlow(0)
    val time: StateFlow<Int> = _time.asStateFlow()

    private val _distance = MutableStateFlow(0.0)
    val distance: StateFlow<Double> = _distance.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    fun startRun() {
        _isRunning.value = true
    }

    fun pauseRun() {
        _isRunning.value = false
    }

    fun updateTime(seconds: Int) {
        _time.value = seconds
    }

    fun updateDistance(value: Double) {
        _distance.value = value
    }
}