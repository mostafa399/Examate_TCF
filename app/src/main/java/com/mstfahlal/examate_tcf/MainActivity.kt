package com.mstfahlal.examate_tcf

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.mstfahlal.examate_tcf.presentation.screens.main_screen.MainScreen
import com.mstfahlal.examate_tcf.presentation.sign_in.GoogleAuthUiClient
import com.mstfahlal.examate_tcf.presentation.sign_in.SignInScreen
import com.mstfahlal.examate_tcf.presentation.sign_in.SignInViewModel
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examate_TCFTheme {
//                MainScreen()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "sign_in") {
                    composable("sign_in") {
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("main_screen")
                            }
                        }
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate("main_screen")
                                viewModel.resetState()
                            }
                        }
                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }
                    composable("main_screen") {
//                            ProfileScreen(
//                                userData = googleAuthUiClient.getSignedInUser(),
//                                onSignOut = {
//                                    lifecycleScope.launch {
//                                        googleAuthUiClient.signOut()
//                                        Toast.makeText(
//                                            applicationContext,
//                                            "Signed out",
//                                            Toast.LENGTH_LONG
//                                        ).show()
//
//                                        navController.popBackStack()
//                                    }
//                                }
//                            )
                        MainScreen(
                            userData = googleAuthUiClient.getSignedInUser(),
                            onSignOutClicked = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.popBackStack()
                                }
                            }
                        )

                    }
                }
            }
        }
    }
}

