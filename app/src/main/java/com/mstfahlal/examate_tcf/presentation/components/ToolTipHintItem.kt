package com.mstfahlal.examate_tcf.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.tooltip.ToolTipView
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
internal fun ToolTipHintItem(
    iconRes: Int,
    textRes: Int,
    isHintVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    customHintContent: (@Composable (RowScope) -> Unit)? = null,
    customContent: (@Composable () -> Unit)? = null,
) {
    val tintColor = if(isHintVisible.value) Examate_TCFTheme.color.primary400 else Examate_TCFTheme.color.contentSecondary

    ToolTipView(
        visibleHintCoordinates = visibleHintCoordinates,
        isHintVisible = isHintVisible,
        dismissOnTouchOutside = false,
        hintBackgroundColor = Examate_TCFTheme.color.secondary800,
        modifier = modifier,
        onClick = onClick,
        horizontalPadding = 10.sdp,
        customHintContent = customHintContent,
        customViewClickable = {
            if(customContent != null){
                customContent()
            }else {
                Column(
                    modifier = Modifier
                        .padding(top = 5.sdp)
                        .clip(Examate_TCFTheme.shapes.large)
                        .width(60.sdp)
                        .height(70.sdp),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(iconRes),
                        modifier = Modifier.size(24.sdp),
                        contentDescription = stringResource(textRes),
                        tint = tintColor,
                    )
                    Spacer(modifier = Modifier.size(Examate_TCFTheme.dimens.space4))
                    Text(
                        text = stringResource(textRes),
                        style = Examate_TCFTheme.typography.medium14,
                        maxLines = 1,
                        color = tintColor
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewToolTipHintItem() {
    val isHintVisible = remember { mutableStateOf(true) }
    val visibleHintCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    Examate_TCFTheme {
        ToolTipHintItem(
            iconRes = R.drawable.ic_oral, // تأكد من استبداله بأيقونة فعلية
            textRes = R.string.tools, // تأكد من استبداله بنص فعلي في ملف strings.xml
            isHintVisible = isHintVisible,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = { isHintVisible.value = !isHintVisible.value }
        )
    }
}