package com.rohandakua.rapidopartnerhelperapp.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.rohandakua.rapidopartnerhelperapp.R
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.getDistanceFeedback
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.getGreetings
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.gradientBrush
import com.rohandakua.rapidopartnerhelperapp.domain.model.DayOfJobUiModel
import com.rohandakua.rapidopartnerhelperapp.navigation.Screen
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.AddRideBox
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.DailyResultBox
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.DailyTargetBox
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.NormalText
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.HomeScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainCardBackground
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier,
    partnerId : Int
) {
    val homeScreenViewModel: HomeScreenViewModel = koinViewModel{ parametersOf(partnerId) }
    val dotLottieController = remember { DotLottieController() }
    val greetingMessage by homeScreenViewModel.greatingMessage.observeAsState()
    val currentJob by homeScreenViewModel.currentJob.observeAsState()
    val isFirstVisit by homeScreenViewModel.isFirstVisit.observeAsState()
    val orientation = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    var showAddRideDialog by remember { mutableStateOf(false) }
    var showEndDayDialog by remember { mutableStateOf(false) }

    if (isFirstVisit == true) {
        DailyTargetBox(
            modifier = modifier,
            showDialog = true,
            onDismiss = { /* Cannot dismiss, must set targets */ },
            onConfirm = { distance, time, earnings ->
                homeScreenViewModel.setDailyTargets(distance, time, earnings)
            },
            greetingText = greetingMessage ?: "Hello",
            isFirstVisit = true
        )
    }

    if (showAddRideDialog) {
        AddRideBox(
            modifier = modifier,
            showDialog = showAddRideDialog,
            onDismiss = { showAddRideDialog = false },
            onConfirm = { distance, timeTaken, fare ->
                homeScreenViewModel.addRide(distance, timeTaken, fare)
                showAddRideDialog = false
            }
        )
    }
    if (showEndDayDialog) {
        DailyResultBox(
            modifier = Modifier,
            showDialog = showEndDayDialog,
            onDismiss = { showEndDayDialog = false },
            onConfirm = {
                homeScreenViewModel.endDay()
                showEndDayDialog = false
            },
            dayOfJobUiModel = currentJob ?: DayOfJobUiModel(
                rapidoPartnerId = partnerId,
                dayOfJob = "",
                totalDistanceCovered = 0.0,
                totalEarnings = 0.0,
                totalTimeTaken = 0,
                totalJobsCompleted = 0,
                targetDistance = 0.0,
                targetEarnings = 0.0,
                targetTime = 0,
                targetJobs = 0,
                resultOfTheDay = null
            ),
            greetingText = greetingMessage ?: "Hello"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // three line
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .padding(top = 30.dp, start = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.threeline),
                        contentDescription = "end day", tint = Color.Black,
                        modifier = Modifier
                            .drawBehind {
                                drawCircle(
                                    brush = gradientBrush,
                                    alpha = 1f
                                )
                            }
                            .clickable {
                                navController.navigate(Screen.Setting.route)
                            }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.60f)
                        .padding(top = 30.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    NormalText(
                        greetingMessage ?: "Hello", textSize = 28
                    )
                }

                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    // rapido logo wihh greetigns
                    DotLottieAnimation(
                        source = DotLottieSource.Url("https://lottie.host/3da2776c-0357-4815-851f-d5a276f5eaa7/63auvFhzz9.lottie"),
                        autoplay = true,
                        loop = true,
                        speed = 1f,
                        useFrameInterpolation = true,
                        playMode = Mode.FORWARD,
                        modifier = Modifier
                            .fillMaxWidth(),
                        controller = dotLottieController
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = mainCardBackground,
                    disabledContainerColor = mainBackgroundColor
                ),
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .fillMaxHeight(.6f)
                    .padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Spacer(Modifier.size(8.dp))
                    NormalText(
                        text = "Distance: ${currentJob?.totalDistanceCovered ?: 0.0} km",
                        textSize = 28
                    )
                    NormalText(
                        text = getDistanceFeedback(currentJob?.totalDistanceCovered ?: 0.0),
                        textSize = 18,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(Modifier.size(20.dp))
                    NormalText(
                        text = "Time: ${currentJob?.totalTimeTaken ?: 0} min",
                        textSize = 28
                    )
                    Spacer(Modifier.size(8.dp))
                    NormalText(
                        text = "Earnings: ₹ ${currentJob?.totalEarnings ?: 0.0}",
                        textSize = 28
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "end day", tint = Color.Black,
                        modifier = Modifier.clickable {
                            showEndDayDialog = true
                        }
                            .drawBehind {
                                drawCircle(
                                    brush = gradientBrush,
                                    alpha = 1f
                                )
                            }
                    )
                    NormalText(
                        text = "End Day",
                        textSize = 15
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_circle_24),
                        contentDescription = "add new ride", tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                showAddRideDialog = true
                            }
                            .drawBehind {
                                drawCircle(
                                    brush = gradientBrush,
                                    alpha = 1f
                                )
                            }
                    )
                    NormalText(
                        text = "Add Ride",
                        textSize = 15
                    )
                }
            }
        }
    }
}