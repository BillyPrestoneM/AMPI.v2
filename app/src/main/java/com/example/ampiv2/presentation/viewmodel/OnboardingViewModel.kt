package com.example.ampiv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ampiv2.domain.usecase.OnboardingUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel (private val onboardingUseCase: OnboardingUseCase) : ViewModel()  {

    fun onboardingCompleted(completed: Boolean) {
        viewModelScope.launch {
            onboardingUseCase.invoke(completed)
        }
    }
}