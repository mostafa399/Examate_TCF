package com.mstfahlal.examate_tcf.presentation.navigation

sealed class Roots(val route: String) {
    data object Home : Roots("Home")
    data object Connect : Roots("Connect")
    data object Questions : Roots("Questions")
    data object Splash : Roots("Splash")
    data object Tools : Roots("Tools")
    data object Profile : Roots("Profile")
}