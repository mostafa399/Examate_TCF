package com.mstfahlal.examate_tcf.presentation.screens.main_screen

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.SharedViewModel
import com.mstfahlal.examate_tcf.presentation.components.ToolTipHintItem
import com.mstfahlal.examate_tcf.presentation.components.ToolTipItem
import com.mstfahlal.examate_tcf.presentation.extensions.navigateToRootScreen
import com.mstfahlal.examate_tcf.presentation.model.MainHintState
import com.mstfahlal.examate_tcf.presentation.navigation.Roots
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomNavigationToolTip(
    navController: NavController,
    modifier: Modifier = Modifier,
    containerColor: Color = Examate_TCFTheme.color.white,
    onUpdateSelectedIndex: (Int) -> Unit,
    mainHintState: MainHintState,
    viewModel: SharedViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    fun resetHints() {
        mainHintState.isHintVisibleHome.value = false
        mainHintState.isHintVisibleConnect.value = false
        mainHintState.isHintVisibleQuestions.value = false
        mainHintState.isHintVisibleTools.value = false
        mainHintState.isHintVisibleProfile.value = false
        mainHintState.isFirstItemHintVisible.value = false
    }

    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp
    ) {
        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_home,
            textRes = R.string.home,
            isHintVisible = mainHintState.isHintVisibleHome,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleHome.value = true
                onUpdateSelectedIndex(0)
                navController.navigateToRootScreen(Roots.Home)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici votre plan d'étude",
                    isHintVisible = mainHintState.isHintVisibleHome,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        coroutineScope.launch {
                            delay(1000)
                            mainHintState.isHintVisibleConnect.value = true
                        }
                        onUpdateSelectedIndex(1)
                        navController.navigateToRootScreen(Roots.Connect)
                    },
                    icon = R.drawable.ic_nav_home
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_connecters,
            textRes = R.string.connect,
            isHintVisible = mainHintState.isHintVisibleConnect,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleConnect.value = true
                onUpdateSelectedIndex(1)
                navController.navigateToRootScreen(Roots.Connect)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici des partenaires d'étude et des personnes avec qui vous connecter",
                    isHintVisible = mainHintState.isHintVisibleConnect,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {

                        resetHints()
                        coroutineScope.launch {
                            delay(1000)
                            mainHintState.isHintVisibleQuestions.value = true
                        }
                        navController.navigateToRootScreen(Roots.Questions)
                        onUpdateSelectedIndex(2)
                    },
                    icon = R.drawable.ic_nav_connecters
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_questions,
            textRes = R.string.questions,
            isHintVisible = mainHintState.isHintVisibleQuestions,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                viewModel.setTabsIndex(0)
                mainHintState.isHintVisibleQuestions.value = true
                onUpdateSelectedIndex(2)
                navController.navigateToRootScreen(Roots.Questions)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Voici les questions avec des réponses modèles",
                    isHintVisible = mainHintState.isHintVisibleQuestions,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        coroutineScope.launch {
                            viewModel.setTabsIndex(0)
                            onUpdateSelectedIndex(2)
                            mainHintState.isFirstItemHintVisible.value = true

                        }

                    },
                    icon = R.drawable.ic_nav_questions
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_tools,
            textRes = R.string.tools,
            isHintVisible = mainHintState.isHintVisibleTools,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleTools.value = true
                onUpdateSelectedIndex(3)
                navController.navigateToRootScreen(Roots.Tools)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = mainHintState.isHintVisibleTools,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        coroutineScope.launch {
                            delay(1000)
                            mainHintState.isHintVisibleProfile.value = true

                        }
                        onUpdateSelectedIndex(4)
                        navController.navigateToRootScreen(Roots.Profile)

                    },
                    icon = R.drawable.ic_nav_tools
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_profile,
            textRes = R.string.profile,
            isHintVisible = mainHintState.isHintVisibleProfile,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleProfile.value = true
                onUpdateSelectedIndex(4)
                navController.navigateToRootScreen(Roots.Profile)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = mainHintState.isHintVisibleProfile,
                    onCloseClick = { viewModel.endTutorial() },
                    icon = R.drawable.ic_nav_profile
                )
            }
        )
    }
}
