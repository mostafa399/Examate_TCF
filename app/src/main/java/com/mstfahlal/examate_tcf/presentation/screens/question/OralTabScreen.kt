package com.mstfahlal.examate_tcf.presentation.screens.question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.SharedViewModel
import com.mstfahlal.examate_tcf.presentation.components.OralQuestionsCard
import com.mstfahlal.examate_tcf.presentation.components.ToolTipHintItem
import com.mstfahlal.examate_tcf.presentation.components.ToolTipItem
import com.mstfahlal.examate_tcf.presentation.extensions.navigateToRootScreen
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.model.questionsList
import com.mstfahlal.examate_tcf.presentation.navigation.Roots
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun OralTabScreen(
    navController: NavController,
    questionsState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    viewModel: SharedViewModel
) {
    val isTutorialActive by viewModel.isTutorialActive.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        item {
            if (isTutorialActive) {
                if (questionsState.isFilterHintVisible.value) {
                    ToolTipHintItem(
                        iconRes = R.drawable.ic_filters,
                        textRes = R.string.questions,
                        isHintVisible = questionsState.isFilterHintVisible,
                        visibleHintCoordinates = visibleHintCoordinates,
                        onClick = {
                            viewModel.updateSelectedIndex(2)
                        },
                        customContent = {
                            Row(
                                modifier = Modifier
                                    .clip(Examate_TCFTheme.shapes.small)
                                    .width(100.sdp)
                                    .height(40.sdp)
                                    .background(Examate_TCFTheme.color.white.copy(alpha = 1f)
                                    ).blur(30.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Filter",
                                    textAlign = TextAlign.Start,
                                    style = Examate_TCFTheme.typography.bold16,
                                    color = Examate_TCFTheme.color.primary600
                                )
                                Icon(
                                    painter = painterResource(R.drawable.ic_filters),
                                    contentDescription = "Filter",
                                    tint = Examate_TCFTheme.color.primary400,
                                    modifier = Modifier.size(20.sdp)
                                )
                            }
                        },
                        customHintContent = {
                            ToolTipItem(
                                hintText = stringResource(R.string.hint),
                                isHintVisible = questionsState.isFilterHintVisible,
                                onCloseClick = {
                                    viewModel.endTutorial()
                                },
                                onNextClick = {
                                    questionsState.isFilterHintVisible.value =
                                        !questionsState.isFilterHintVisible.value
                                    viewModel.updateSelectedIndex(3)
                                    coroutineScope.launch {
                                        questionsState.isToolsHintVisible.value =
                                            !questionsState.isToolsHintVisible.value
                                    }

                                    navController.navigateToRootScreen(Roots.Tools)
                                },
                                icon = R.drawable.ic_filters
                            )
                        }
                    )
                } else {
                    // Default display without the hint
                    Row(
                        modifier = Modifier
                            .clip(Examate_TCFTheme.shapes.small)
                            .width(100.sdp)
                            .height(40.sdp)
                            .background(Examate_TCFTheme.color.secondary400),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Filter",
                            textAlign = TextAlign.Start,
                            style = Examate_TCFTheme.typography.bold16,
                            color = Examate_TCFTheme.color.primary600
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_filters),
                            contentDescription = "Filter",
                            tint = Examate_TCFTheme.color.primary400,
                            modifier = Modifier.size(20.sdp)
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .clip(Examate_TCFTheme.shapes.small)
                        .width(100.sdp)
                        .height(40.sdp)
                        .background(Examate_TCFTheme.color.secondary400),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Filter",
                        textAlign = TextAlign.Start,
                        style = Examate_TCFTheme.typography.bold16,
                        color = Examate_TCFTheme.color.primary600
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_filters),
                        contentDescription = "Filter",
                        tint = Examate_TCFTheme.color.primary400,
                        modifier = Modifier.size(20.sdp)
                    )
                }
            }
        }
        items(questionsList) {
            OralQuestionsCard(it)
        }
    }
}
