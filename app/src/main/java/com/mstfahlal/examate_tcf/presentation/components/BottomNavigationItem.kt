package com.mstfahlal.examate_tcf.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.tooltip.utils.DEFAULT_PADDING
import co.yml.tooltip.utils.MAX_LINE
import co.yml.tooltip.utils.TOOLTIP_MAX_WIDTH_PERCENT
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.extensions.noIndicationClickable
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp


@Composable
fun BottomNavigationItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: Int,
    text: Int,
    onClick: () -> Unit,
) {
    val tintColor = if (selected) Examate_TCFTheme.color.primary400 else Examate_TCFTheme.color.contentSecondary
    val textStyle = if (selected) Examate_TCFTheme.typography.bold14 else Examate_TCFTheme.typography.medium12
    val iconSize = if(selected) 22.sdp else 18.sdp

    Column(
        modifier = modifier.noIndicationClickable(onClick = onClick),
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            modifier = Modifier.size(iconSize),
            contentDescription = "Bottom Navigation Item Icon",
            tint = tintColor,
        )
        Spacer(modifier = Modifier.size(Examate_TCFTheme.dimens.space4))
        Text(
            text = stringResource(text),
            style = textStyle,
            maxLines = 1,
            color = tintColor
        )
    }
}

@Composable
fun ToolTipItem(
    hintText: String,
    isHintVisible: MutableState<Boolean>,
    onNextClick: (() -> Unit)? = null,
    onCloseClick: (() -> Unit)? = null,
    icon : Int,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp * 0.8f
    Column(
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = hintText,
                maxLines = MAX_LINE,
                color = Color.White,
                style = Examate_TCFTheme.typography.medium14,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(DEFAULT_PADDING)
                    .widthIn(max = (TOOLTIP_MAX_WIDTH_PERCENT * screenWidth).dp)
            )
            Text(
                text = stringResource(R.string.close),
                color = Color.White,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(DEFAULT_PADDING)
                    .clickable {
                        onCloseClick?.invoke()
                        isHintVisible.value = !isHintVisible.value
                    }
            )
        }

        if (onNextClick != null)
            Text(
                text = stringResource(R.string.next),
                color = Color.White,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(DEFAULT_PADDING)
                    .clickable {
                        onNextClick()
                    }
            )
    }
}
@Preview(showBackground = true )
@Composable
fun PreviewBottomNavigationItem() {
    BottomNavigationItem(
        selected = true,
        icon = R.drawable.ic_nav_home,
        text = R.string.home,
        onClick = {}
    )
}