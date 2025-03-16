package com.mstfahlal.examate_tcf.presentation.screens.question

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.SharedViewModel
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.screens.ScreenContainer
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
internal fun QuestionsScreen(
    navController: NavController,
    questionState: QuestionScreenHintState,
    viewModel: SharedViewModel = hiltViewModel()
) = ScreenContainer {
    val visibleHintCoordinates = remember { mutableStateOf(viewModel.visibleHintCoordinates.value) }
    QuestionsTabs(navController, questionState, visibleHintCoordinates, viewModel)
}

@Composable
fun QuestionsTabs(
    navController: NavController,
    questionState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    viewModel: SharedViewModel
) {
    val tabIndex by viewModel.questionsTabsIndex.collectAsState()
    val tabs = listOf(stringResource(R.string.writing), stringResource(R.string.oral))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            modifier = Modifier.width(240.sdp),
            selectedTabIndex = tabIndex,
            containerColor = Examate_TCFTheme.color.background,
            contentColor = Examate_TCFTheme.color.primary400,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = Examate_TCFTheme.color.primary400
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.sdp)
                    ) {
                        if (index == 0) {
                            Icon(
                                painter = painterResource(R.drawable.ic_writing),
                                modifier = Modifier.size(18.sdp),
                                tint = Examate_TCFTheme.color.primary400,
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.ic_oral),
                                modifier = Modifier.size(18.sdp),
                                tint = Examate_TCFTheme.color.primary400,
                                contentDescription = ""
                            )
                        }
                        Text(
                            title,
                            style = Examate_TCFTheme.typography.bold16,
                            color = Examate_TCFTheme.color.primary400
                        )
                    }
                },
                    selected = tabIndex == index,
                    onClick = { viewModel.setTabsIndex(index) }
                )
            }
        }
        when (tabIndex) {
            0 -> WritingTabScreen(
                questionState,
                visibleHintCoordinates
            )

            1 -> OralTabScreen(
                navController = navController,
                questionState,
                visibleHintCoordinates,
                viewModel
            )
        }
    }
}



