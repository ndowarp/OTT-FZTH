package com.example.fzthtv.views.composables.poster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.globant.fzth.domain.models.Movie
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LandingPoster(
    movie: Movie,
    navController: NavController,
    onClick: () -> Unit = {}
) {
    val localContext = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(221.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize()
                .blur(radius = 16.dp),
            model = ImageRequest.Builder(localContext).data(movie.posterURL)
                .crossfade(true).scale(Scale.FILL).build(),
            contentDescription = movie.getMovieTitle(),
                    contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(221.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(localContext).data(movie.posterURL)
                    .crossfade(true).scale(Scale.FILL).build(),
                contentDescription = movie.getMovieTitle()
            )
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart,
            ) {
                Text(
                    text = movie.getMovieTitle(),
                    fontSize = 22.sp,
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .offset(
                            x = 2.dp,
                            y = 2.dp
                        )
                        .alpha(0.75f)
                )
            }
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    onClick = {
                        val encodedUrl = URLEncoder.encode(
                            movie.thumbnailURL,
                            StandardCharsets.UTF_8.toString()
                        )
                        navController.navigate(
                            "detail/${movie.overview}/${movie.mediaType}/${movie.title}/${encodedUrl}/${movie.voteAverage}"
                        )
                    }) {
                    Text(text = "Go", color = Color.White)
                }
            }
        }
    }
}
