package com.rohandakua.rapidopartnerhelperapp.presentation.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
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
import androidx.navigation.NavHostController
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.navigation.Screen
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.GradientText
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.NormalText
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.LoginScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.SettingScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.ui.theme.LexendDeca
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainCardBackground
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainTextColor
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    settingScreenViewModel: SettingScreenViewModel = koinViewModel(),
    loginScreenViewModel: LoginScreenViewModel = koinViewModel()
    ) {

    var dotLottieController = remember { DotLottieController() }

    var rapidoPartnerId = loginScreenViewModel.rapidoPartnerIdText
    var password = loginScreenViewModel.passwordText
    val loginResult by loginScreenViewModel.loginResult.observeAsState()
    val animationSource = remember {
        DotLottieSource.Url("https://lottie.host/3da2776c-0357-4815-851f-d5a276f5eaa7/63auvFhzz9.lottie")
    }


    val firstFocusRequester = remember { FocusRequester() }
    val secondFocusRequester = remember { FocusRequester() }

    LaunchedEffect(true) {
        firstFocusRequester.requestFocus()
    }
    LaunchedEffect(loginResult) {
        if (loginResult==true) {
            navController.navigate(Screen.HomeWithPartnerId.createRoute(rapidoPartnerId)) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)  // Color(0xFF10110f)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top

            ) {
                // rapido logo wihh greetigns
                DotLottieAnimation(
                    source = animationSource,
                    autoplay = true,
                    loop = true,
                    speed = 1f,
                    useFrameInterpolation = true,
                    playMode = Mode.FORWARD,
                    modifier = Modifier
                        .size(400.dp)
                        .fillMaxWidth(),
                    controller = dotLottieController
                )
            }







            OutlinedTextField(
                value = rapidoPartnerId,
                onValueChange = {
                    loginScreenViewModel.rapidoPartnerIdText = it

                },
                label = {
                    NormalText(
                        text = "Rapido Partner Id",
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = mainTextColor,
                    unfocusedTextColor = mainTextColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green
                )
            )


            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    loginScreenViewModel.passwordText = it

                },
                label = { NormalText(text = "Password", textSize = 18) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if(rapidoPartnerId.length == 6 && password.length >= 8){
                            loginScreenViewModel.login()

                        }

                    }
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = LexendDeca,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.focusRequester(secondFocusRequester),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = mainTextColor,
                    unfocusedTextColor = mainTextColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green
                )
            )

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = mainCardBackground,
                    disabledContainerColor = mainBackgroundColor
                ),
                modifier = Modifier.clickable {
                    if(rapidoPartnerId.length == 6 && password.length >= 8){
                        Log.d("loginscreen" , "rapidoPartnerId: $rapidoPartnerId, password: $password")
                        loginScreenViewModel.login()
                        loginScreenViewModel.userProfile.value?.let {
                            val rapidoPartner = RapidoPartner.fromUiModel(it)
                            settingScreenViewModel.saveUser(rapidoPartner)
                        }



                    }
                }
                    .width(150.dp)
                    .height(70.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    GradientText(text = "Login", textSize = 30)

                }


            }


        }


    }
}