package com.mstfahlal.examate_tcf.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.domain.model.connectios.ConnectItemModel
import com.mstfahlal.examate_tcf.domain.model.connectios.UserModel
import com.mstfahlal.examate_tcf.presentation.components.ConnectCardItem
import com.mstfahlal.examate_tcf.presentation.model.connectionsList
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun ConnectScreen() = ScreenContainer {
    Tabs()
}

@Composable
fun Tabs() {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(R.string.suggestions), stringResource(R.string.chat))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
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
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> Suggestions()
            1 -> Chat()
        }
    }
}


@Composable
fun Suggestions() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Suggested Study Partners",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    style = Examate_TCFTheme.typography.bold18,
                    color = Examate_TCFTheme.color.primary600
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filters),
                        contentDescription = "Home",
                        tint = Examate_TCFTheme.color.primary400,
                        modifier = Modifier
                            .size(24.sdp)
                    )
                }
            }
        }
        items(connectionsList) {
            ConnectCardItem(it)
        }
    }
}

@Composable
fun Chat() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        items(connectionsList) {
            ConnectCardItem(it)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewConnectScreen() {
    Examate_TCFTheme {
        ConnectScreen()
    }
}
