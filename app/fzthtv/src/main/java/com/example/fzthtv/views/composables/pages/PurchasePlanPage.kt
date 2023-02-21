package com.example.fzthtv.views.composables.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fzthtv.R
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fzthtv.events.PaymentEvents
import com.example.fzthtv.viewmodel.PaymentViewModel
import com.globant.fzth.domain.models.CreditCard


@Composable
fun PurchasePlanPage(
    navController: NavController,
    viewModel: PaymentViewModel = hiltViewModel()
) {

    val maxChar = 3
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var cardOwnerName by rememberSaveable { mutableStateOf("") }
    var expirationDate by rememberSaveable { mutableStateOf("") }
    var securityCode by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(32.dp),
                text = "Choose your Subscription", fontSize = 26.sp,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                ),
            )
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Subscription(
                    navController,
                    viewModel,
                    cardNumber,
                    cardOwnerName,
                    expirationDate,
                    securityCode,
                    "With Ads",
                    "Save 16%",
                    "$9.99",
                    "Exclusive streaming access to the biggest Warner Bros. movies of 2022 at no extra cost\n" +
                            "Watch everything with limited ads for a lower price"
                )
                Subscription(
                    navController,
                    viewModel,
                    cardNumber,
                    cardOwnerName,
                    expirationDate,
                    securityCode,
                    "Ad-Free",
                    price = "$14.99",
                    description = "Exclusive streaming access to the biggest Warner Bros. movies of 2022 at no extra cost\n " + "" +
                            "Download your favorites to watch on-the-go Stream in 4K UHD (as available)",
                )
            }
        }
        Column(
            modifier = Modifier
                .size(450.dp,350.dp)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .size(380.dp, 550.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black)
                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .size(380.dp, 680.dp)
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Payment Information", fontSize = 22.sp,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.Default
                        ),
                    )
                    TextField(
                        value = cardNumber,
                        onValueChange = {
                            cardNumber = it
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_credit_card_24),
                                contentDescription = "Card Number",
                                tint = Color.DarkGray
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = { number ->
                            formatOtherCardNumbers(number)
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
                                text = "Card Number",
                                color = Color.Black
                            )
                        },
                        placeholder = { Text(text = "1234-123-1312-1231") },
                        modifier = Modifier
                            .height(70.dp)
                            .width(320.dp)
                            .padding(5.dp)
                    )
                    TextField(
                        value = cardOwnerName,
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = "Card Owner Name",
                                tint = Color.DarkGray
                            )
                        },
                        onValueChange = {
                            cardOwnerName = it
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
                                text = "Card Owner Name",
                                color = Color.Black
                            )
                        },
                        placeholder = { Text(text = "Enter Name") },
                        modifier = Modifier
                            .height(70.dp)
                            .width(320.dp)
                            .padding(5.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(0.dp, 30.dp)
                            .size(340.dp, 600.dp)
                    ) {
                        TextField(
                            value = expirationDate,
                            onValueChange = {
                                expirationDate = it
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_date_range_24),
                                    contentDescription = "Expiration Date",
                                    tint = Color.DarkGray
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = { number ->
                                formatExpirationDate(number)
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
                                    text = "Exp Date",
                                    color = Color.Black
                                )
                            },
                            placeholder = { Text(text = "01/28") },
                            modifier = Modifier
                                .height(70.dp)
                                .width(130.dp)
                        )
                        TextField(
                            value = securityCode,
                            onValueChange = {
                                if (it.length <= maxChar) {
                                    securityCode = it
                                }
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_lock_24),
                                    contentDescription = "Security Code",
                                    tint = Color.DarkGray
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                                    text = "Security Code",
                                    color = Color.Black
                                )
                            },
                            placeholder = { Text(text = "123") },
                            modifier = Modifier
                                .height(70.dp)
                                .width(130.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Subscription(
    navController: NavController,
    viewModel: PaymentViewModel = hiltViewModel(),
    cardNumber: String,
    cardOwnerName: String,
    expirationDate: String,
    securityCode: String,
    title: String,
    extra: String = "",
    price: String,
    description: String
) {
    Card(
        modifier = Modifier
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(15.dp)),
        border = BorderStroke(2.dp, Color.White),
        elevation = 6.dp,
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = Color.Black,
        contentColor = Color.Black

    ) {
        Column(
            modifier = Modifier
                .size(180.dp, 300.dp)
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
            )
            Text(
                text = price,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.caption,
                maxLines = 1
            )
            Text(
                text = extra,
                color = Color.White,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.caption,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = description,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    viewModel.onEvent(event = PaymentEvents.ValidatePayment(CreditCard(cardNumber, cardOwnerName ,expirationDate,securityCode)))
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Purchase", color = Color.White)
                if (viewModel.state.value.creditCard?.cardNumber.equals("222222222222222222222")) {
                    Log.d("CREATION", viewModel.state.value.errorMessage.toString())
                    Toast.makeText(
                        LocalContext.current,
                        "There was an error with your credit card, we couldn't make the payment!",
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.state.value.creditCard = CreditCard("","","","")
                }
                if (viewModel.state.value.creditCard?.cardNumber.equals("111111111111111111111")) {
                    viewModel.state.value.creditCard = CreditCard("","","","")
                    viewModel.state.value.creditCard?.cardNumber
                    navController.navigate("landing_page")
                }
            }
        }
    }
}

fun formatOtherCardNumbers(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += "-"
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}


fun formatExpirationDate(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i != 3) out += "/"
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            return 3
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}