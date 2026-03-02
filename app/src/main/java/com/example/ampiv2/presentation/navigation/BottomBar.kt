package com.example.ampiv2.presentation.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ampiv2.di.utils.BotNavScreen
import com.example.ampiv2.di.utils.noRippleClickable
import com.example.ampiv2.presentation.ui.theme.blue
import com.example.ampiv2.presentation.ui.theme.gray

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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        screens.forEach { screen ->

            val isSelected = currentRoute == screen.route
            val itemColor by animateColorAsState(
                targetValue = if (isSelected) blue else gray,
                label = ""
            )

            if (screen is BotNavScreen.Add) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(screen.icon ?: 0),
                        contentDescription = screen.title,
                        modifier = Modifier.size(38.dp),
                        tint = blue
                    )
                }

            } else {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        painter = painterResource(
                            if (isSelected)
                                screen.selectedIcon ?: screen.icon ?: 0
                            else
                                screen.icon ?: 0
                        ),
                        contentDescription = screen.title,
                        modifier = Modifier.size(22.dp),
                        tint = itemColor
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = screen.title ?: "",
                        color = itemColor,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}