package com.example.ampiv2

import android.app.Application
import com.example.ampiv2.di.modules.OnboardingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AMPIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AMPIApplication)
            modules(OnboardingModule)
        }
    }
}