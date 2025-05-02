package com.rohandakua.rapidopartnerhelperapp.presentation.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rohandakua.rapidopartnerhelperapp.R
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.gradientBrush
import com.rohandakua.rapidopartnerhelperapp.navigation.Screen
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.NormalText
import com.rohandakua.rapidopartnerhelperapp.presentation.composables.RatingBar
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.SettingScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainBackgroundColor
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainCardBackground
import com.rohandakua.rapidopartnerhelperapp.ui.theme.secondaryTextColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
//@Preview
fun SettingScreen (
    modifier: Modifier = Modifier,
    navController: NavHostController,
    settingScreenViewModel: SettingScreenViewModel = koinViewModel()

    ){
    var user = settingScreenViewModel.user // take it from the user object
    var name = user!!.value!!.name ?: "name" // // take it from the user object
    var rating = user!!.value!!.rating?: 5.0 // take it from the user object
    val earnings : Double = user.value!!.earning?: 1500.0 // take it from the user object in setting screen view model
    var darkMode : StateFlow<Boolean> =  settingScreenViewModel.darkMode
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor)  // Color(0xFF10110f)
            .safeDrawingPadding()
    ) {
        Column(
            modifier =  Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = mainCardBackground,
                    disabledContainerColor = mainBackgroundColor
                ),
                modifier = Modifier
                    .fillMaxWidth( .95f)
                    .fillMaxHeight(.25f)
                    .padding(horizontal = 10.dp , vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // for Name
                    NormalText(text = name , textSize = 33)
                    Spacer(Modifier.size(20.dp))
                    // ratings
                    RatingBar(rating = rating , starSize = 33.dp)


                }


            }

            Row (
                modifier =Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceEvenly ,
                verticalAlignment = Alignment.CenterVertically
            ){
                NormalText(text  = "Total Earnings" , textSize = 25)
                NormalText(text = earnings.toString() + " rs", textSize = 25)
            }


            // Dark Mode toggle
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(.6f).padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                NormalText(text = "Dark Mode", textSize = 20)
                Switch(checked = darkMode.value?: true, onCheckedChange = {
                    settingScreenViewModel.saveDarkMode(it)
                }, thumbContent = {
                    Icon(painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                        contentDescription = "check",
                        tint = Color.Black,
                        modifier = Modifier
                            .drawBehind {
                                drawCircle(
                                    brush = gradientBrush, alpha = 1f
                                )
                            }
                            .size(25.dp)
                            .clickable {
                                settingScreenViewModel.saveDarkMode(!darkMode.value)
                            })
                }, colors = SwitchDefaults.colors(
                    checkedTrackColor = secondaryTextColor,
                    uncheckedTrackColor = mainCardBackground
                )

                )
            }



            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                NormalText(text = "Made with ❤️ by Rohan Dakua", textSize = 16)
                Spacer(modifier = Modifier.height(10.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "logout",
                    tint = Color.Black,
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                brush = gradientBrush,
                                alpha = 1f,
                                radius = this.size.minDimension / 2 + 10
                            )
                        }
                        .size(25.dp)
                        .clickable {
                           settingScreenViewModel.logoutUser()
                            navController.navigate(Screen.Login.route)
                        }

                )
                NormalText(text = "Logout", textSize = 24)
            }






        }

    }
}