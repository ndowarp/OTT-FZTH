package com.example.fzthtv.views.composables.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import kotlin.math.roundToLong


@Composable
fun DetailPage(
    navController: NavController,
    overview: String?,
    mediaType: String?,
    title: String?,
    thumbnailURL: String?,
    voteAverage: Float?
) {
    val localContext = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize()
                        .blur(radius = 16.dp),
                    model = ImageRequest.Builder(localContext).data(thumbnailURL)
                        .crossfade(true).scale(Scale.FILL).build(),
                    contentDescription = title,
                    contentScale = ContentScale.Crop
                )
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(localContext).data(thumbnailURL)
                            .crossfade(true).scale(Scale.FILL).build(),
                        contentDescription = title,
                        modifier = Modifier
                            .height(250.dp)
                            .padding(start = 5.dp, end = 5.dp)
                            .clip(MaterialTheme.shapes.large)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillHeight
                    )
                    IconButton(
                        modifier = Modifier
                            .size(92.dp)
                            .align(Alignment.Center),
                        onClick = { navController.navigate("video_player") },
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(92.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                Text(
                    text = title!!,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    overflow = TextOverflow.Clip,
                    maxLines = 2,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                )
                Divider(
                    thickness = 1.dp,
                    color = Color.Red
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp, top = 10.dp)
                ) {
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Language",
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "EN",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Rating",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = voteAverage!!.roundToLong().toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(
                    text = "Description",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = overview!!,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 10.dp, bottom = 8.dp)
                )

            }
        }
    }
}