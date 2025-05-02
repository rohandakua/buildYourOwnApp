package com.rohandakua.rapidopartnerhelperapp.presentation.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.rohandakua.rapidopartnerhelperapp.domain.model.DayOfJobUiModel
import com.rohandakua.rapidopartnerhelperapp.ui.theme.LexendDeca
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainCardBackground
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainTextColor

@Composable
@Preview
fun DailyResultBox(
    modifier: Modifier = Modifier,
    showDialog: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    greetingText: String = "Good AfterNoon",
    dayOfJobUiModel: DayOfJobUiModel = DayOfJobUiModel(
        rapidoPartnerId = 0,
        dayOfJob = "11-11-2025",
        totalDistanceCovered = 187.5,
        totalEarnings = 2350.0,
        totalTimeTaken = 320,
        totalJobsCompleted = 12,
        targetDistance = 100.0,
        targetEarnings = 1500.0,
        targetTime = 240,
        targetJobs = 15,
        resultOfTheDay = false
    )

) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)  // Color(0xFF10110f)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = mainCardBackground,
                    disabledContainerColor = mainBackgroundColor
                ),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .fillMaxHeight(.8f)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = onDismiss,
                            title = {
                                Column {
                                    NormalText(text = greetingText, textSize = 28)
                                    Spacer(Modifier.size(30.dp))
                                    NormalText(
                                        text = "Your Daily Summary",
                                        textSize = 24
                                    )
                                }
                            },
                            text = {
                                Column {
                                    Spacer(Modifier.size(8.dp))
                                    NormalText(
                                        text = "Distance: ${dayOfJobUiModel.totalDistanceCovered} km / ${dayOfJobUiModel.targetDistance} km",
                                        textSize = 18
                                    )
                                    Spacer(Modifier.size(8.dp))
                                    NormalText(
                                        text = "Time: ${dayOfJobUiModel.totalTimeTaken} min / ${dayOfJobUiModel.targetTime} min",
                                        textSize = 18
                                    )
                                    Spacer(Modifier.size(8.dp))
                                    NormalText(
                                        text = "Earnings: ₹${dayOfJobUiModel.totalEarnings} / ₹${dayOfJobUiModel.targetEarnings}",
                                        textSize = 18
                                    )

                                    Spacer(modifier = Modifier.height(24.dp))

                                    val achievedAll = dayOfJobUiModel.totalDistanceCovered!! >= dayOfJobUiModel.targetDistance!! &&
                                            dayOfJobUiModel.totalTimeTaken!! >= dayOfJobUiModel.targetTime!! &&
                                            dayOfJobUiModel.totalEarnings!! >= dayOfJobUiModel.targetEarnings!!

                                    NormalText(
                                        text = if (achievedAll) "🎉 Congratulations!" else "🙁 Better luck next time!",
                                        textSize = 20
                                    )
                                }
                            },
                            confirmButton = {
                                Button(
                                    onClick = { onDismiss() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = mainCardBackground,
                                        disabledContainerColor = Color.Gray
                                    )
                                ) {
                                    NormalText(text = "OK", textSize = 18)
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = onDismiss,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = mainCardBackground,
                                        disabledContainerColor = mainCardBackground
                                    )
                                ) {
                                    NormalText(text = "Cancel", textSize = 18)
                                }
                            },
                            containerColor = mainBackgroundColor,
                            properties = DialogProperties(
                                dismissOnBackPress = true,
                                dismissOnClickOutside = true
                            )
                        )



                    }


                }


            }
        }
    }
}









