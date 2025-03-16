package com.mstfahlal.examate_tcf.presentation.screens.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.components.BottomNavigationItem
import com.mstfahlal.examate_tcf.presentation.extensions.navigateToRootScreen
import com.mstfahlal.examate_tcf.presentation.navigation.Roots
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme

@Composable
fun BottomNavigation(
    navController: NavController,
    currentSelectedScreen: Roots,
    modifier: Modifier = Modifier,
    containerColor: Color = Examate_TCFTheme.color.white,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp,
    ) {
        BottomNavigationItem(
            selected = currentSelectedScreen == Roots.Home,
            onClick = { navController.navigateToRootScreen(Roots.Home) },
            icon = R.drawable.ic_nav_home,
            text = R.string.home,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            selected = currentSelectedScreen == Roots.Connect,
            onClick = { navController.navigateToRootScreen(Roots.Connect) },
            icon = R.drawable.ic_nav_connecters,
            text = R.string.connect,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            selected = currentSelectedScreen == Roots.Questions,
            onClick = { navController.navigateToRootScreen(Roots.Questions) },
            icon = R.drawable.ic_nav_questions,
            text = R.string.questions,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            selected = currentSelectedScreen == Roots.Tools,
            onClick = { navController.navigateToRootScreen(Roots.Tools) },
            icon = R.drawable.ic_nav_tools,
            text = R.string.tools,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            selected = currentSelectedScreen == Roots.Profile,
            onClick = { navController.navigateToRootScreen(Roots.Profile) },
            icon = R.drawable.ic_nav_profile,
            text = R.string.profile,
            modifier = Modifier.weight(1f)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val fakeNavController = rememberNavController()

    BottomNavigation(
        navController = fakeNavController,
        currentSelectedScreen = Roots.Home,
        modifier = Modifier.fillMaxWidth()
    )
}