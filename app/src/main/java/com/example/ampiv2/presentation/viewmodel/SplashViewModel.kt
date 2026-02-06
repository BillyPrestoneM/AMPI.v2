package com.example.ampiv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ampiv2.data.datastore.onboardingDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel (
    private val onboardingDataStore: onboardingDataStore
): ViewModel() {
    private val _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted: StateFlow<Boolean> = _onboardingCompleted

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady

    init {
        viewModelScope.launch {
            val state = onboardingDataStore.getOnboardingState()
            _onboardingCompleted.value = state
            _isReady.value = true
        }
    }
}