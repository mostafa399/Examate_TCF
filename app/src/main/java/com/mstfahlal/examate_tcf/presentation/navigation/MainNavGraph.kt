package com.mstfahlal.examate_tcf.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.screens.ConnectScreen
import com.mstfahlal.examate_tcf.presentation.screens.HomeScreen
import com.mstfahlal.examate_tcf.presentation.screens.question.QuestionsScreen

@Composable
fun MainNavGraph(navController: NavHostController, startDestination: String) {

    val graphModifier = Modifier
        .fillMaxSize()
        .imePadding()


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = graphModifier
    ) {
        homeScreen(navController)
        connectScreen(navController)
        questionsScreen(navController)
        profileScreen(navController)
       // splashScreen(navController)
        toolsScreen(navController)
    }
}


private fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = Roots.Home.route) {
        HomeScreen()
    }
}

private fun NavGraphBuilder.connectScreen(navController: NavController) {
    composable(route = Roots.Connect.route) {
        ConnectScreen()
    }
}

private fun NavGraphBuilder.questionsScreen(navController: NavController) {
    composable(route = Roots.Questions.route) {
        QuestionsScreen(navController=navController,QuestionScreenHintState())
    }
}

//private fun NavGraphBuilder.splashScreen(
//    navController: NavController,
//) {
//    composable(route = Roots.Splash.route) {
//        SplashScreen {
//            navController.navigate(route = Roots.Home.route,
//                navOptions = navOptions {
//                    popUpTo(Roots.Splash.route) {
//                        inclusive = true
//                    }
//                }
//            )
//        }
//    }
//}

private fun NavGraphBuilder.profileScreen(navController: NavController) {
    composable(route = Roots.Profile.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Profile Screen ")
        }
    }
}

private fun NavGraphBuilder.toolsScreen(navController: NavController) {
    composable(route = Roots.Tools.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Tools Screen")
        }
    }
}