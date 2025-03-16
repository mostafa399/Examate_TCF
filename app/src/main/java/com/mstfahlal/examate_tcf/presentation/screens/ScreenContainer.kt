package com.mstfahlal.examate_tcf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenContainer(content: @Composable ()->Unit) {
    Box(Modifier.fillMaxSize()) {
        content()
    }
}