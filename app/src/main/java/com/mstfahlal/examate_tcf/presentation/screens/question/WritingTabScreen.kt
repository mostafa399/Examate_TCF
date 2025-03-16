package com.mstfahlal.examate_tcf.presentation.screens.question

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.domain.model.questions.WritingQuestionsModel
import com.mstfahlal.examate_tcf.presentation.SharedViewModel
import com.mstfahlal.examate_tcf.presentation.components.ToolTipHintItem
import com.mstfahlal.examate_tcf.presentation.components.ToolTipItem
import com.mstfahlal.examate_tcf.presentation.model.QuestionScreenHintState
import com.mstfahlal.examate_tcf.presentation.model.writingsList
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun WritingTabScreen(
    questionScreenHintState: QuestionScreenHintState,
    visibleCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.sdp)
    ) {
        CustomTabSample(questionScreenHintState, visibleCoordinates)
    }

}
@Composable
fun CustomTabSample(
    questionScreenHintState: QuestionScreenHintState,
    visibleCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
) {

    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }

    CustomTab(
        items = listOf("Task 1", "Task 2"),
        selectedItemIndex = selected,
        modifier = Modifier.padding(horizontal = 10.sdp),
        onClick = setSelected,
    )

    when (selected) {
        0 -> QuestionsGridList(questionScreenHintState, visibleCoordinates)
        1 -> QuestionsGridList(questionScreenHintState, visibleCoordinates)
    }
}

@Composable
private fun QuestionsGridList(
    questionScreenHintState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
    viewModel: SharedViewModel = hiltViewModel(),
) {
    val isTutorial by viewModel.isTutorialActive.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp),
        horizontalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        items(writingsList) {
            if (writingsList.indexOf(it) == 0 && isTutorial) {
                ToolTipHintItem(
                    iconRes = R.drawable.ic_filters,
                    textRes = R.string.questions,
                    isHintVisible = questionScreenHintState.isFirstItemHintVisible,
                    visibleHintCoordinates = visibleHintCoordinates,
                    onClick = {

                    },
                    customContent = {
                        WritingQuestionsCard(it)
                    },
                    customHintContent = {
                        ToolTipItem(
                            hintText = stringResource(R.string.hint),
                            isHintVisible = questionScreenHintState.isFirstItemHintVisible,
                            onCloseClick = {
                                viewModel.endTutorial()
                            },
                            onNextClick = {
                                questionScreenHintState.isFirstItemHintVisible.value =
                                    !questionScreenHintState.isFirstItemHintVisible.value
                                coroutineScope.launch {
                                    delay(1000)
                                    viewModel.setTabsIndex(1)
                                    questionScreenHintState.isFilterHintVisible.value =
                                        !questionScreenHintState.isFilterHintVisible.value

                                }
                            },
                            icon = R.drawable.ic_filters
                        )
                    }
                )
            } else {
                WritingQuestionsCard(it)
            }
        }
    }
}


@Composable
fun WritingQuestionsCard(
    questionItem: WritingQuestionsModel,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Examate_TCFTheme.color.white),
        shape = Examate_TCFTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.sdp, top = 7.sdp, bottom = 7.sdp, end = 25.sdp),
            verticalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Surface(
                color = Examate_TCFTheme.color.secondary400,
                shape = Examate_TCFTheme.shapes.small,
                modifier = Modifier
                    .height(15.sdp)
            ) {
                Text(
                    text = "${questionItem.answersCount} sur ${questionItem.questionsCount} Questions",
                    style = Examate_TCFTheme.typography.medium12,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Examate_TCFTheme.color.primary600,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Icon(
                    painter = painterResource(questionItem.icon),
                    contentDescription = "Home",
                    tint = Examate_TCFTheme.color.black,
                    modifier = Modifier
                        .size(18.sdp)
                )
                Text(
                    text = questionItem.type,
                    style = Examate_TCFTheme.typography.bold16,
                    textAlign = TextAlign.Start,
                    color = Examate_TCFTheme.color.primary200,
                )
            }
            Text(
                text = "Progress ${questionItem.progress.toInt()} %",
                style = Examate_TCFTheme.typography.medium12,
                textAlign = TextAlign.Center,
                color = Examate_TCFTheme.color.primary200,
            )
            CustomLinearIndicator(
                progress = questionItem.progress,
                modifier = Modifier.fillMaxWidth().padding(7.sdp),
            )
        }
    }
}
@Composable
private fun CustomLinearIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = Examate_TCFTheme.color.secondary200,
    progressColor: Color = Examate_TCFTheme.color.primary400,
    strokeWidth: Dp = 6.sdp,
) {
    val normalizesdprogress = (progress.coerceIn(0f, 100f)) / 100f

    Canvas(modifier = modifier.height(strokeWidth)) {
        val width = size.width
        val height = size.height

        drawLine(
            color = trackColor,
            start = Offset(0f, height / 2),
            end = Offset(width, height / 2),
            strokeWidth = height,
            cap = StrokeCap.Round
        )

        drawLine(
            color = progressColor,
            start = Offset(0f, height / 2),
            end = Offset(width * normalizesdprogress, height / 2),
            strokeWidth = height,
            cap = StrokeCap.Round
        )
    }
}@Composable
fun CustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    onClick: (index: Int) -> Unit,
) {
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Examate_TCFTheme.color.secondary400)
            .height(intrinsicSize = IntrinsicSize.Min),
    ) {
        MyTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = Examate_TCFTheme.color.primary600,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clip(CircleShape),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                MyTabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabWidth,
                    text = text,
                )
            }
        }
    }
}
@Composable
private fun MyTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing), label = "",
    )
    Text(
        modifier = Modifier.clip(CircleShape)
            .clickable {
                onClick()
            }
            .width(tabWidth)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp,
            ),
        text = text,
        color = tabTextColor,
        textAlign = TextAlign.Center,
    )
}
@Composable
fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width = indicatorWidth)
            .offset(x = indicatorOffset)
            .clip(shape = CircleShape)
            .background(color = indicatorColor),
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewWritingQuestionsCard() {
    Examate_TCFTheme {
        WritingQuestionsCard(
            questionItem = WritingQuestionsModel(
                icon = R.drawable.ic_answers,
                type = "Expression Écrite",
                answersCount = 3,
                questionsCount = 10,
                progress = 50f
            )
        )
    }
}
