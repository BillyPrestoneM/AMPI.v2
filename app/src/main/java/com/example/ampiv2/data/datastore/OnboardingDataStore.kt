package com.example.ampiv2.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private  val Context.dataStore by preferencesDataStore(name = "user_pref")

class onboardingDataStore (private val context: Context) {

    companion object {
        private val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")

    }

    suspend fun saveOnboardingState(isCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = isCompleted
        }

    }

    suspend fun getOnboardingState(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[ONBOARDING_KEY] ?: false
    }


}