package com.mstfahlal.examate_tcf.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.components.ProgressSteps
import com.mstfahlal.examate_tcf.presentation.sign_in.UserData
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun HomeScreen(userName: UserData?=UserData(userId = "1","mos","")) = ScreenContainer {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.sdp),
    ) {

            TextTile(userName?.username.toString())

        Text(
            text = stringResource(R.string.study_plan),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 12.sdp),
            style = Examate_TCFTheme.typography.bold24,
            color = Examate_TCFTheme.color.primary600,
        )
        ProgressSteps()
    }
}


@Composable
fun TextTile(
    title: String,
    textStyle: TextStyle = Examate_TCFTheme.typography.bold24,
    textColor: Color = Examate_TCFTheme.color.primary600,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.sdp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.sdp)
    ) {
        Text(
            text = stringResource(R.string.hi),
            textAlign = TextAlign.Start,
            style = Examate_TCFTheme.typography.medium24,
            color = textColor
        )
        Text(
            text = title,
            textAlign = TextAlign.Start,
            style = textStyle,
            color = textColor
        )
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Examate_TCFTheme {
        HomeScreen()
    }
}
