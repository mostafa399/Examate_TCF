package com.mstfahlal.examate_tcf.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import kotlinx.coroutines.delay
import network.chaintech.sdpcomposemultiplatform.sdp
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    navigateOut: () -> Unit
) = ScreenContainer {



    Column(
        Modifier
            .fillMaxSize()
            .background(Examate_TCFTheme.color.black.copy(alpha = 0.7f))

    ) {
        val modifier = remember {
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 14.dp)
        }
        Spacer(Modifier.height( 180.sdp))
        Text(
            modifier = modifier,
            text = "welcome to:  how to use and enjoy Examate",
            style = Examate_TCFTheme.typography.medium18,
            textAlign = TextAlign.Center,
            color = Examate_TCFTheme.color.white
        )
        Spacer(Modifier.height( 24.sdp))
        Text(
            modifier = modifier,
            text = "Tap anywhere on the screen to continue",
            style = Examate_TCFTheme.typography.medium24,
            textAlign = TextAlign.Center,
            color = Examate_TCFTheme.color.blue
        )
    }

}