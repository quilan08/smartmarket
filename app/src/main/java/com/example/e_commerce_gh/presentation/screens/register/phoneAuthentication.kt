import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import com.example.e_commerce_gh.presentation.screens.register.VerifyCodeUiEvent
import com.example.e_commerce_gh.presentation.screens.register.VerifyCodeUiState

import com.example.e_commerce_gh.presentation.screens.register.VerifyViewModel

@Composable
fun PhoneAuthScreen(navController: NavController, viewModel: VerifyViewModel = hiltViewModel()) {
    val uiState by viewModel.verifyCodeUiState.collectAsState()

    var phoneNumber by remember { mutableStateOf(uiState.phoneNumber) }
    var verificationCode by remember { mutableStateOf(uiState.verificationCode) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Phone Authentication", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                viewModel.onEvent(VerifyCodeUiEvent.SubmitChanged(phoneNumber))
            },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isCodeSent) {
            TextField(
                value = verificationCode,
                onValueChange = {
                    verificationCode = it
                    viewModel.onEvent(VerifyCodeUiEvent.CodeChanged(it))
                },
                label = { Text("Verification Code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.verifyCode() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify Code")
            }
        } else {
            Button(
                onClick = { viewModel.authenticatePhoneNumber(context as Activity) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Code")
            }
        }
    }
}
