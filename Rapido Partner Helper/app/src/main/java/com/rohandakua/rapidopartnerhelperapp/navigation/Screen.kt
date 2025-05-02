package com.rohandakua.rapidopartnerhelperapp.navigation

sealed class Screen (val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object HomeWithPartnerId : Screen("home_screen/{partnerId}"){
        fun createRoute(partnerId: String) = "home_screen/$partnerId"
    }
    object Setting : Screen("setting_screen")
    object Login : Screen("login_screen")


}