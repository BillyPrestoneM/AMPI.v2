package com.example.ampiv2.presentation.ui.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ampiv2.di.utils.BotNavScreen
import com.example.ampiv2.presentation.ui.theme.blue
import com.example.ampiv2.presentation.ui.theme.gray
import com.example.ampiv2.presentation.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun OnboardingScreen(
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = koinViewModel()
) {
    //create list onboarding screen page
    val onboardingPages = listOf(
        OnboardingScreenPage.First,
        OnboardingScreenPage.Second,
        OnboardingScreenPage.Third
    )

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
            ) { position ->
                OnboardingScreenPager(
                    onboardingScreenPages = onboardingPages[position],
                    pageIndex = position
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = onboardingPages.size,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = blue,
                inactiveColor = Color.LightGray,
            )
            FinishButton(
                modifier = Modifier.padding(32.dp),
                pagerState = pagerState
            ) {
                coroutineScope.launch {
                    onboardingViewModel.onboardingCompleted(true)
                    navController.popBackStack()
                    navController.navigate(BotNavScreen.Home.route)
                }
            }
        }
        if (pagerState.currentPage != 2) {
            Text(
                text = "Lewati",
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(2)
                            onboardingViewModel.onboardingCompleted(true)
                            navController.navigate(BotNavScreen.Home.route)
                        }
                    },
                color = blue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium

            )
        }
    }
}

@Composable
fun OnboardingScreenPager(
    onboardingScreenPages: OnboardingScreenPage,
    pageIndex: Int,
) {

    val isThirdPage = pageIndex == 2

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        // ini kuncinya
        verticalArrangement =
            if (isThirdPage)
                Arrangement.Top
            else
                Arrangement.Center

    ) {

        Image(
            modifier = if (isThirdPage) {
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            } else {
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(300.dp)
            },
            painter = painterResource(id = onboardingScreenPages.image),
            contentDescription = null
        )


        if (isThirdPage) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingScreenPages.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingScreenPages.description,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

        } else {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingScreenPages.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingScreenPages.description,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = blue,
    inactiveColor: Color = Color.LightGray
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) {iteration ->
            val color = if (pagerState.currentPage == iteration) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Mulai Sekarang",
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {

}