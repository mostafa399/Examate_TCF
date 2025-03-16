package com.mstfahlal.examate_tcf.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.presentation.screens.ScreenContainer
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    ScreenContainer {
        Column(
            Modifier
                .fillMaxSize()
                .background(Examate_TCFTheme.color.background)

        ) {
            val modifier = remember {
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 14.dp)
            }
            Spacer(Modifier.height( 180.sdp))
            Text(
                modifier = modifier,
                text = "welcome to Examate",
                style = Examate_TCFTheme.typography.bold24,
                textAlign = TextAlign.Center,
                color = Examate_TCFTheme.color.blue
            )
            Spacer(Modifier.height( 24.sdp))
            Text(
                modifier = modifier,
                text = "Study For Your Exam ",
                style = Examate_TCFTheme.typography.medium24,
                textAlign = TextAlign.Center,
                color = Examate_TCFTheme.color.black
            )
            Spacer(Modifier.height( 24.sdp))
            GoogleSignInButton(onClick = onSignInClick)
        }


    }
}
@Composable
private fun GoogleSignInButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_google),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Sign In With Google",
            color = Color.Black,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        state = SignInState(signInError = null),
        onSignInClick = {}
    )
}
