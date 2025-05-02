package com.rohandakua.rapidopartnerhelperapp.presentation.screen




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.rohandakua.rapidopartnerhelperapp.R
import com.rohandakua.rapidopartnerhelperapp.navigation.Screen
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.LoginScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

//@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginScreenViewModel: LoginScreenViewModel = koinViewModel(),
) {
    val isSignedIn = loginScreenViewModel.loginResult
    val dotLottieController = remember { DotLottieController() }
    val animationComplete = remember { mutableStateOf(false)
    }

    LaunchedEffect(dotLottieController) {
        while (!dotLottieController.isComplete) {
            delay(400) // Check every 400ms to avoid blocking UI
        }
        animationComplete.value = true
    }

    LaunchedEffect(animationComplete.value) {
        if (animationComplete.value) {
            if (isSignedIn.value == true) {
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Splash.route){inclusive = true}
                }
            } else {
                navController.navigate(Screen.Login.route){
                    popUpTo(Screen.Splash.route){inclusive = true}
                }

            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)
    ) {

    }

    Box(modifier = Modifier.safeDrawingPadding()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            DotLottieAnimation(
                source = DotLottieSource.Url("https://lottie.host/3da2776c-0357-4815-851f-d5a276f5eaa7/63auvFhzz9.lottie"),
                autoplay = true,
                loop = false,
                speed = 1f,
                useFrameInterpolation = true,
                playMode = Mode.FORWARD,
                modifier = Modifier
                    .fillMaxSize(0.8f),
                controller = dotLottieController
            )
        }
    }

}