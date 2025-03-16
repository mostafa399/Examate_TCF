package com.mstfahlal.examate_tcf.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.domain.model.questions.OralQuestionsModel
import com.mstfahlal.examate_tcf.domain.model.questions.WritingQuestionsModel
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun OralQuestionsCard(
    questionItem: OralQuestionsModel
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Examate_TCFTheme.color.white),
        shape = Examate_TCFTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.sdp),
            verticalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OralQuestionsTextItem(modifier = Modifier.weight(1f), questionItem.titles)
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "Home",
                        tint = Examate_TCFTheme.color.black,
                        modifier = Modifier
                            .size(24.sdp)
                    )
                }
            }
            Text(
                text = questionItem.question,
                style = Examate_TCFTheme.typography.medium14,
                color = Examate_TCFTheme.color.contentPrimary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(3.sdp)
                        .height(30.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.sdp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_answers),
                        contentDescription = "",
                        modifier = Modifier.size(16.sdp)
                    )
                    Text(
                        text = "${questionItem.answersCount} answers",
                        style = Examate_TCFTheme.typography.medium12,
                        color = Examate_TCFTheme.color.primary200
                    )
                }
                Text(
                    text = questionItem.date,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.sdp),
                    style = Examate_TCFTheme.typography.medium12,
                    color = Examate_TCFTheme.color.primary200
                )
            }
        }
    }
}

@Composable
private fun OralQuestionsTextItem(modifier: Modifier = Modifier, titles: List<String>) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.sdp),
    ) {
        titles.forEach {
            Surface(
                color = Examate_TCFTheme.color.background,
                shape = Examate_TCFTheme.shapes.small,
                modifier = Modifier
                    .width(60.sdp)
                    .height(15.sdp)
            ) {
                Text(
                    text = it,
                    style = Examate_TCFTheme.typography.medium12,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Examate_TCFTheme.color.contentPrimary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOralQuestionsCard() {
    Examate_TCFTheme {
        OralQuestionsCard(
            questionItem = OralQuestionsModel(
                titles = listOf("A1", "B2"),
                question = "Quel est votre avis sur l'éducation en ligne ?",
                answersCount = 15,
                date = "12 Mars 2025"
            )
        )
    }
}


