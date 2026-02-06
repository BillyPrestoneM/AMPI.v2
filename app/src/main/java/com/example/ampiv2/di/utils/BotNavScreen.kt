package com.example.ampiv2.di.utils

import com.example.ampiv2.R

sealed class BotNavScreen (
    val route: String,
    val title: String? = null,
    val icon: Int? = null
) {
    data object Onboarding : BotNavScreen("onboarding_screen")

    data object Home : BotNavScreen("home_screen", "Home", R.drawable.home)
    data object Profile : BotNavScreen("profile_screen", "Profile", R.drawable.person)
    data object Add : BotNavScreen("add_screen", "Add", R.drawable.tambah)
    data object MoneyManagement : BotNavScreen("money_management_screen", "Money Management", R.drawable.payments)
    data object TimeManagement : BotNavScreen("time_management_screen", "Time Management", R.drawable.event)
}