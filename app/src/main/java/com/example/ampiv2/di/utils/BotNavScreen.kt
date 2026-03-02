package com.example.ampiv2.di.utils

import com.example.ampiv2.R

sealed class BotNavScreen (
    val route: String,
    val title: String? = null,
    val icon: Int? = null,
    val selectedIcon: Int? = null
) {
    data object Onboarding : BotNavScreen("onboarding_screen")

    data object Home : BotNavScreen("home_screen", "Home", R.drawable.home, R.drawable.home_filled)
    data object Profile : BotNavScreen("profile_screen", "Profile", R.drawable.person, R.drawable.person_filled)
    data object Add : BotNavScreen("add_screen", "", R.drawable.add_circle_filled)
    data object MoneyManagement : BotNavScreen("money_management_screen", "Money", R.drawable.payments, R.drawable.payments_filled)
    data object TimeManagement : BotNavScreen("time_management_screen", "Time", R.drawable.event, R.drawable.event_filled)
}