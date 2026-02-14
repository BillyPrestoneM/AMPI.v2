package com.example.ampiv2.di.modules

import com.example.ampiv2.data.datastore.onboardingDataStore
import com.example.ampiv2.domain.usecase.OnboardingUseCase
import com.example.ampiv2.presentation.viewmodel.OnboardingViewModel
import com.example.ampiv2.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val OnboardingModule = module {
    single { onboardingDataStore(get()) }
    factory { OnboardingUseCase(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}