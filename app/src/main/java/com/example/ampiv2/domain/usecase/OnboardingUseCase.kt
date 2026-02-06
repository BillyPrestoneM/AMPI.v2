package com.example.ampiv2.domain.usecase

import com.example.ampiv2.data.datastore.onboardingDataStore

class OnboardingUseCase (private val onboardingDataStore: onboardingDataStore) {
    suspend fun invoke(completed: Boolean) {
        onboardingDataStore.saveOnboardingState(completed)
    }
}