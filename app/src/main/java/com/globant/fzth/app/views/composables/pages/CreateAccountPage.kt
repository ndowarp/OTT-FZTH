package com.globant.fzth.app.views.composables.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.globant.fzth.app.R
import com.globant.fzth.app.events.AuthEvents
import com.globant.fzth.app.viewmodel.AuthViewModel

@Composable
fun CreateAccountPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.n_logo_create_account_alt),
            contentDescription = stringResource(id = R.string.netflix_logo),
            modifier = Modifier.height(320.dp),
            alignment = Alignment.Center,
        )
        Text(
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 20.dp),
            text = "One more step to create your account!", fontSize = 22.sp,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            textAlign = TextAlign.Center
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,

                ),
            maxLines = 1,
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "E-mail",
                    color = Color.Black
                )
            },
            placeholder = { Text(text = "Enter e-mail") },
            modifier = Modifier
                .height(70.dp)
                .width(320.dp)
                .padding(5.dp)
        )
        TextField(
            value = password,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                        contentDescription = "Visible password",
                        tint = Color.DarkGray
                    )
                }
            },
            onValueChange = {
                password = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
            ),
            maxLines = 1,
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Password",
                    color = Color.Black
                )
            },
            placeholder = { Text(text = "Enter Password") },
            modifier = Modifier
                .height(70.dp)
                .width(320.dp)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .size(340.dp, 600.dp)
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(event = AuthEvents.CreateUser(email, password))
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Create Account", color = Color.White)
                if (viewModel.state.value.errorMessage) {
                    viewModel.state.value.errorMessage = false
                    Toast.makeText(LocalContext.current, "There was an error we couldn't create your account!", Toast.LENGTH_LONG).show()
                }
                if (viewModel.state.value.successMessage) {
                    viewModel.state.value.successMessage = false
                    navController.navigate("purchase_plan")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(10.dp),
                text = "By creating your account you agree to our terms and conditions, check our terms and conditions by clicking here",
                fontSize = 10.sp,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
fun PreviewCreateAccount() {
    //CreateAccountPage()
}