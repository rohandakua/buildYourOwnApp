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
import com.rohandakua.rapidopartnerhelperapp.ui.theme.LexendDeca
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainCardBackground
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainTextColor

@Composable
fun DailyTargetBox(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Double, Int, Double) -> Unit,
    greetingText: String,
    isFirstVisit: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)
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
                    var distance by remember { mutableStateOf("") }
                    var timeTaken by remember { mutableStateOf("") }
                    var fare by remember { mutableStateOf("") }

                    var distanceError by remember { mutableStateOf<String?>(null) }
                    var timeTakenError by remember { mutableStateOf<String?>(null) }
                    var fareError by remember { mutableStateOf<String?>(null) }

                    val firstFocusRequester = remember { FocusRequester() }
                    val secondFocusRequester = remember { FocusRequester() }
                    val thirdFocusRequester = remember { FocusRequester() }

                    LaunchedEffect(showDialog) {
                        if (showDialog) {
                            firstFocusRequester.requestFocus()
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = onDismiss,
                            title = {
                                Column {
                                    NormalText(text = greetingText, textSize = 28)
                                    Spacer(Modifier.size(10.dp))
                                    NormalText(
                                        text = "Set Daily Target",
                                        textSize = 24
                                    )
                                }
                            },
                            text = {
                                Column {
                                    OutlinedTextField(
                                        value = distance,
                                        onValueChange = {
                                            distance = it
                                            distanceError =
                                                if (it.toDoubleOrNull() == null || it.toDoubleOrNull()!! <= 0) {
                                                    "Enter a valid distance"
                                                } else {
                                                    null
                                                }
                                        },
                                        label = {
                                            NormalText(
                                                text = "Target distance in kms",
                                                textSize = 18
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onNext = { secondFocusRequester.requestFocus() }
                                        ),
                                        singleLine = true,
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = LexendDeca,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.focusRequester(firstFocusRequester),
                                        isError = distanceError != null,
                                        colors = TextFieldDefaults.colors(
                                            focusedTextColor = mainTextColor,
                                            unfocusedTextColor = mainTextColor,
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedIndicatorColor = if (distanceError != null) Color.Red else mainTextColor,
                                            unfocusedIndicatorColor = if (distanceError != null) Color.Red else mainTextColor
                                        )
                                    )
                                    distanceError?.let {
                                        Text(it, color = Color.Red, fontSize = 12.sp)
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedTextField(
                                        value = timeTaken,
                                        onValueChange = {
                                            timeTaken = it
                                            timeTakenError =
                                                if (it.toIntOrNull() == null || it.toIntOrNull()!! <= 0) {
                                                    "Enter a valid time in minutes"
                                                } else {
                                                    null
                                                }
                                        },
                                        label = { NormalText(text = "Target time in mins", textSize = 18) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Done
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onNext = { thirdFocusRequester.requestFocus() }
                                        ),
                                        singleLine = true,
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = LexendDeca,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.focusRequester(secondFocusRequester),
                                        isError = timeTakenError != null,
                                        colors = TextFieldDefaults.colors(
                                            focusedTextColor = mainTextColor,
                                            unfocusedTextColor = mainTextColor,
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedIndicatorColor = if (timeTakenError != null) Color.Red else mainTextColor,
                                            unfocusedIndicatorColor = if (timeTakenError != null) Color.Red else mainTextColor
                                        )
                                    )
                                    timeTakenError?.let {
                                        Text(it, color = Color.Red, fontSize = 12.sp)
                                    }

                                    Spacer(Modifier.size(8.dp))
                                    OutlinedTextField(
                                        value = fare,
                                        onValueChange = {
                                            fare = it
                                            fareError =
                                                if (it.toDoubleOrNull() == null || it.toDoubleOrNull()!! <= 0) {
                                                    "Enter a valid fare"
                                                } else {
                                                    null
                                                }
                                        },
                                        label = { NormalText(text = "Target fare in rs", textSize = 18) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onDone = {
                                                val distanceValue = distance.toDoubleOrNull()
                                                val timeTakenValue = timeTaken.toIntOrNull()
                                                val fareValue = fare.toDoubleOrNull()

                                                if (distanceValue != null && distanceValue > 0 && timeTakenValue != null && timeTakenValue > 0 && fareValue != null && fareValue > 0) {
                                                    onConfirm(
                                                        distanceValue,
                                                        timeTakenValue,
                                                        fareValue
                                                    )
                                                    onDismiss()
                                                } else {
                                                    distanceError = "Enter a valid distance"
                                                    timeTakenError = "Enter a valid time"
                                                    fareError = "Enter a valid fare"
                                                }
                                            }
                                        ),
                                        singleLine = true,
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = LexendDeca,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.focusRequester(thirdFocusRequester),
                                        isError = fareError != null,
                                        colors = TextFieldDefaults.colors(
                                            focusedTextColor = mainTextColor,
                                            unfocusedTextColor = mainTextColor,
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedIndicatorColor = if (fareError != null) Color.Red else mainTextColor,
                                            unfocusedIndicatorColor = if (fareError != null) Color.Red else mainTextColor
                                        )
                                    )
                                    fareError?.let {
                                        Text(it, color = Color.Red, fontSize = 12.sp)
                                    }
                                }
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val distanceValue = distance.toDoubleOrNull()
                                        val timeTakenValue = timeTaken.toIntOrNull()
                                        val fareValue = fare.toDoubleOrNull()

                                        if (distanceValue != null && distanceValue > 0 && timeTakenValue != null && timeTakenValue > 0 && fareValue != null && fareValue > 0) {
                                            onConfirm(distanceValue, timeTakenValue, fareValue)
                                            onDismiss()
                                        } else {
                                            distanceError = "Enter a valid distance"
                                            timeTakenError = "Enter a valid time"
                                            fareError = "Enter a valid fare"
                                        }
                                    },
                                    enabled = distance.toDoubleOrNull() != null && distance.toDoubleOrNull()!! > 0 &&
                                            timeTaken.toIntOrNull() != null && timeTaken.toIntOrNull()!! > 0 &&
                                            fare.toDoubleOrNull() != null && fare.toDoubleOrNull()!! > 0,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = mainCardBackground,
                                        disabledContainerColor = Color.Gray
                                    )
                                ) {
                                    NormalText(text = "OK", textSize = 18)
                                }
                            },
                            dismissButton = if (!isFirstVisit) {
                                {
                                    Button(
                                        onClick = onDismiss,
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = mainCardBackground,
                                            disabledContainerColor = mainCardBackground
                                        )
                                    ) {
                                        NormalText(text = "Cancel", textSize = 18)
                                    }
                                }
                            } else null,
                            containerColor = mainBackgroundColor,
                            properties = DialogProperties(
                                dismissOnBackPress = !isFirstVisit,
                                dismissOnClickOutside = !isFirstVisit
                            )
                        )
                    }
                }
            }
        }
    }
}









