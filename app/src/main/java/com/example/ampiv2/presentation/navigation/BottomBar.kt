package com.example.ampiv2.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ampiv2.di.utils.BotNavScreen

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screens = listOf(
        BotNavScreen.Home,
        BotNavScreen.MoneyManagement,
        BotNavScreen.Add,
        BotNavScreen.TimeManagement,
        BotNavScreen.Profile
    )

    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier
            .height(72.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        screens.forEach { screen ->
            val isSelected = currentRoute == screen.route
            val itemColor = when (screen) {
                is BotNavScreen.Add -> Color.Blue
                else -> if (isSelected) Color.Blue else Color.Gray
            }

            NavigationBarItem(
                icon = {
                    screen.icon?.let{iconRes ->
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = screen.title,
                            tint = if (screen is BotNavScreen.Add) Color.Blue else itemColor
                        )
                    }
                },
                label = {
                    screen.title?.let { title ->
                        Text(
                            text = title,
                            color = itemColor
                        )
                    }
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}