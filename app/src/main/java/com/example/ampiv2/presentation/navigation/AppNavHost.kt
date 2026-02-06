package com.example.ampiv2.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ampiv2.di.utils.BotNavScreen
import com.example.ampiv2.presentation.ui.screen.onboarding.OnboardingScreen
import com.example.ampiv2.presentation.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    val splashViewModel: SplashViewModel = koinViewModel()
    val onboardingCompleted by splashViewModel.onboardingCompleted.collectAsState()

    val startDestination = if (!onboardingCompleted) {
        BotNavScreen.Onboarding.route
    } else {
        BotNavScreen.Home.route
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in listOf(
        BotNavScreen.Home.route,
        BotNavScreen.MoneyManagement.route,
        BotNavScreen.Add.route,
        BotNavScreen.TimeManagement.route,
        BotNavScreen.Profile.route,
    )

    Scaffold(bottomBar = {
        if (showBottomBar) {
            BottomBar(navController = navController)
        }
    }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BotNavScreen.Onboarding.route) {
                OnboardingScreen(navController = navController)
            }

        }
    }
}