package com.mstfahlal.examate_tcf.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val localColorScheme = staticCompositionLocalOf { examateThemeColors }
private val localDimens = staticCompositionLocalOf { Dimens() }
private val localTypography = staticCompositionLocalOf { examateTypography }
private val localShapes = staticCompositionLocalOf { shapes }

val purple = Color(0xFFAF61E2)
val darkPurple = Color(0xFF2D115A)
val black = Color(0xFF010101)

@Composable
fun Examate_TCFTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localColorScheme provides examateThemeColors,
        localDimens provides Dimens(),
        localTypography provides examateTypography,
        localShapes provides shapes
    ) {
        content()
    }
}

object Examate_TCFTheme {

    val color: ExamateColor
        @Composable
        get() = localColorScheme.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current

    val typography: ExamateTypography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val shapes: ExamateShapes
        @Composable
        @ReadOnlyComposable
        get() = localShapes.current
}