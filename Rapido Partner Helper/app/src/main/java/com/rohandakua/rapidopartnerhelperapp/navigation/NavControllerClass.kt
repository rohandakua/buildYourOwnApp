package com.rohandakua.rapidopartnerhelperapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rohandakua.rapidopartnerhelperapp.presentation.screen.HomeScreen
import com.rohandakua.rapidopartnerhelperapp.presentation.screen.LoginScreen
import com.rohandakua.rapidopartnerhelperapp.presentation.screen.SettingScreen
import com.rohandakua.rapidopartnerhelperapp.presentation.screen.SplashScreen


@Composable
fun NavControllerClass(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ){
        composable(
            route = Screen.Splash.route
        ){
            SplashScreen(navController = navController , modifier = modifier)
        }
        composable(
            route = "home_screen/{partnerId}",
            arguments = listOf(navArgument("partnerId") {type = NavType.IntType})
        ){  backStackEntry ->
            val partnerId = backStackEntry.arguments?.getInt("partnerId") ?: 0
            HomeScreen(navController = navController, modifier = modifier, partnerId = partnerId)
        }

        composable(
            route = Screen.Setting.route
        ) {
            SettingScreen(navController = navController, modifier = modifier)
        }

        composable(
            route = Screen.Login.route
        ){
            LoginScreen(navController = navController, modifier = modifier)
        }
       }

}