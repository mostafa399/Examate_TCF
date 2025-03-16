package com.mstfahlal.examate_tcf.presentation.screens.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.screens.ConnectScreen
import com.mstfahlal.examate_tcf.presentation.screens.HomeScreen
import com.mstfahlal.examate_tcf.presentation.screens.question.QuestionsScreen
import com.mstfahlal.examate_tcf.presentation.screens.ScreenContainer
import com.mstfahlal.examate_tcf.presentation.sign_in.UserData

@Composable
fun ContentSection(
    navController: NavController,
    selectedIndex: Int=0,
    userData: UserData?,
    questionScreenHintState: QuestionScreenHintState =
        QuestionScreenHintState(
            isFilterHintVisible = remember { mutableStateOf(false) },
            isToolsHintVisible = remember { mutableStateOf(false) },
            isFirstItemHintVisible = remember { mutableStateOf(false) }
        )
) {
    when (selectedIndex) {
        0 -> {
            HomeScreen(userData)
        }

        1 -> {
            ConnectScreen()
        }

        2 -> {
            QuestionsScreen(navController = navController, questionScreenHintState)
        }

        3 -> {
            ScreenContainer {
                Text(
                    text = "Tools",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }

        4 -> {
            ScreenContainer {
                Text(
                    text = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}