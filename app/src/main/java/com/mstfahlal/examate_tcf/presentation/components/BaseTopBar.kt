package com.mstfahlal.examate_tcf.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.navigation.Roots
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    navController: NavController,
    topBarColor: Color=Examate_TCFTheme.color.background,
    onSignOutClicked: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val title = when (currentRoute) {
        Roots.Home.route -> R.string.home
        Roots.Tools.route -> R.string.tools
        Roots.Connect.route -> R.string.connect
        Roots.Profile.route -> R.string.profile
        Roots.Questions.route -> R.string.questions
        else -> R.string.empty
    }


    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarColor,
            titleContentColor = Examate_TCFTheme.color.primary800,
        ),
        title = {
            TopBarTitle(title = title)
        },
        actions = {
            if (currentRoute == Roots.Home.route) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Home",
                        tint = Examate_TCFTheme.color.primary400,
                        modifier = Modifier
                            .size(24.sdp)
                    )
                }

            }
            if (currentRoute == Roots.Profile.route) {
                    // Add a sign-out icon
                    IconButton(onClick = onSignOutClicked) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp, // Use a logout icon from Material Icons
                            contentDescription = "Sign Out",
                            tint = Examate_TCFTheme.color.primary400,
                            modifier = Modifier
                                .size(24.sdp)
                        )
                    }
            }
        }
    )

}


@Composable
fun TopBarTitle(
    title: Int,
    textStyle: TextStyle = Examate_TCFTheme.typography.bold24,
    textColor: Color = Examate_TCFTheme.color.primary600,
) {
    Text(
        text = stringResource(title),
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
        style = textStyle,
        color = textColor
    )
}

@Preview(showBackground = true, name = "Profile Screen Top Bar")
@Composable
fun PreviewBaseTopBarProfile() {
    val navController = rememberNavController()
    Examate_TCFTheme {
        BaseTopBar(navController = navController, onSignOutClicked = { /* TODO: Handle sign out */ })
    }
}