package com.mstfahlal.examate_tcf.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import co.yml.tooltip.ui.ToolTipScreen
import co.yml.tooltip.ui.isTipVisible
import com.mstfahlal.examate_tcf.presentation.SharedViewModel
import com.mstfahlal.examate_tcf.presentation.components.BaseTopBar
import com.mstfahlal.examate_tcf.presentation.extensions.currentScreenAsState
import com.mstfahlal.examate_tcf.presentation.model.MainHintState
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.navigation.MainNavGraph
import com.mstfahlal.examate_tcf.presentation.navigation.Roots
import com.mstfahlal.examate_tcf.presentation.screens.TapToContinueScreen
import com.mstfahlal.examate_tcf.presentation.sign_in.UserData
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSignOutClicked: () -> Unit,
    userData:UserData?,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentScreen by navController.currentScreenAsState()

    val bottomBarHeight = 90.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val bottomBarColor = Examate_TCFTheme.color.white


    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val isTutorialActive by sharedViewModel.isTutorialActive.collectAsState()
    val visibleHintCoordinates: MutableState<LayoutCoordinates?> = remember { mutableStateOf(null) }
    val isHintVisibleHome = remember { mutableStateOf(true) }
    val isHintVisibleConnect = remember { mutableStateOf(false) }
    val isHintVisibleQuestions = remember { mutableStateOf(false) }
    val isHintVisibleTools = remember { mutableStateOf(false) }
    val isFirstItemHintVisible = remember { mutableStateOf(false) }
    val isHintVisibleProfile = remember { mutableStateOf(false) }
    val isFilterHintVisible = remember { mutableStateOf(false) }
    val selectedIndex by sharedViewModel.selectedIndex.collectAsState()
    var isSplashFinished by remember { mutableStateOf(false) }
    var tapCount by remember { mutableStateOf(0) }
    if (isTutorialActive) {
        if (!isSplashFinished) {
                    ToolTipScreen(
                        paddingHighlightArea = 0f,
                        backgroundTransparency = 0.7f,
                        cornerRadiusHighlightArea = 0f,
                        mainContent = {
                            Scaffold(
                                modifier = modifier.background(Color.Black.copy(alpha = 0.7f)).alpha(.7f),
                                topBar = { BaseTopBar(navController,topBarColor=Color.Black.copy(alpha = 0.05f),onSignOutClicked)},
                                bottomBar = {
                                    BottomNavigation(
                                        modifier = Modifier.alpha(.9f),
                                        navController = navController,
                                        currentSelectedScreen = Roots.Home,
                                        containerColor = Color.Black.copy(alpha = 0.05f)

                                    )

                                },
                                content = {
                                    Box(Modifier.padding(it)) {
                                        ContentSection(navController=navController,userData = userData)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Black.copy(alpha = 0.7f))
                                    ) {
                                        TapToContinueScreen(){
                                            tapCount++
                                            if (tapCount >= 2) {
                                                isSplashFinished = true
                                            }
                                        }
                                    }
                                },
                            )

                        },
                        anyHintVisible = false,
                        visibleHintCoordinates = visibleHintCoordinates
                    )



        }
        if (isSplashFinished) {
            ToolTipScreen(
                paddingHighlightArea = 0f,
                backgroundTransparency = 0.7f,
                cornerRadiusHighlightArea = 0f,
                mainContent = {
                    Scaffold(
                        modifier = modifier.nestedScroll(nestedScrollConnection),
                        containerColor = Examate_TCFTheme.color.background,
                        topBar = { BaseTopBar(navController, onSignOutClicked = onSignOutClicked) },
                        bottomBar = {
                            BottomNavigationToolTip(
                                navController = navController,
                                modifier = Modifier
                                    .height(bottomBarHeight),
                                onUpdateSelectedIndex = {
                                    sharedViewModel.updateSelectedIndex(it)
                                },
                                mainHintState = MainHintState(
                                    visibleHintCoordinates = visibleHintCoordinates,
                                    isHintVisibleProfile = isHintVisibleProfile,
                                    isHintVisibleHome = isHintVisibleHome,
                                    isHintVisibleConnect = isHintVisibleConnect,
                                    isHintVisibleQuestions = isHintVisibleQuestions,
                                    isHintVisibleTools = isHintVisibleTools,
                                    isFirstItemHintVisible = isFirstItemHintVisible,
                                ),
                                viewModel = sharedViewModel
                            )
                        }, content = {
                            Box(Modifier.padding(it)) {
                                ContentSection(
                                    navController=navController,
                                    selectedIndex,
                                    userData = userData,
                                    QuestionScreenHintState(
                                        isFilterHintVisible,
                                        isHintVisibleTools,
                                        isFirstItemHintVisible
                                    )
                                )
                            }
                        }
                    )
                },
                anyHintVisible = isTipVisible(
                    isHintVisibleHome,
                    isHintVisibleConnect,
                    isHintVisibleQuestions,
                    isHintVisibleTools,
                    isFirstItemHintVisible,
                    isFilterHintVisible,
                    isHintVisibleProfile
                ).value,
                visibleHintCoordinates = visibleHintCoordinates,
            )
        }
    } else {
        Scaffold(
            modifier = modifier.nestedScroll(nestedScrollConnection),
            containerColor = Examate_TCFTheme.color.background,
            topBar = {
                BaseTopBar(navController, onSignOutClicked = onSignOutClicked)
            },
            bottomBar = {
                BottomNavigation(
                        navController = navController,
                        currentSelectedScreen = currentScreen,
                        containerColor = bottomBarColor
                    )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                MainNavGraph(navController = navController, startDestination = Roots.Home.route)
            }
        }
    }
}