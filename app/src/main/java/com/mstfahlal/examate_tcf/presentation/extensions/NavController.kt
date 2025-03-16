package com.mstfahlal.examate_tcf.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mstfahlal.examate_tcf.presentation.navigation.Roots

@Stable
@Composable
fun NavController.currentScreenAsState(): State<Roots> {
    val selectedItem = remember { mutableStateOf<Roots>(Roots.Home) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Roots.Home.route } -> {
                    selectedItem.value = Roots.Home
                }

                destination.hierarchy.any { it.route == Roots.Connect.route } -> {
                    selectedItem.value = Roots.Connect
                }

                destination.hierarchy.any { it.route == Roots.Questions.route } -> {
                    selectedItem.value = Roots.Questions
                }

                destination.hierarchy.any { it.route == Roots.Tools.route } -> {
                    selectedItem.value = Roots.Tools
                }

                destination.hierarchy.any { it.route == Roots.Profile.route } -> {
                    selectedItem.value = Roots.Profile
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

fun NavController.navigateToRootScreen(roots: Roots) {
    navigate(roots.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(Roots.Home.route) {
            inclusive = false
        }
    }
}