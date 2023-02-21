package com.globant.fzth.app.views.composables.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.globant.fzth.domain.models.Movie
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.math.roundToLong

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun GenderSection(
    navController: NavController,
    title: String,
    movies: List<Movie>,
    onClick: (Movie) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 2)
    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = title,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(3.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalPager(
            count = movies.size,
            state = pagerState,
            itemSpacing = 5.dp,
            contentPadding = PaddingValues(30.dp, 2.dp, 30.dp, 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .size(width = 250.dp, height = 140.dp)
                        .indication(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(bounded = true)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 15.dp,
                    onClick = {
                        val encodedUrl = URLEncoder.encode(
                            movies[page].thumbnailURL,
                            StandardCharsets.UTF_8.toString()
                        )
                        navController.navigate(
                            "detail/${movies[page].overview}/${movies[page].mediaType}/${movies[page].title}/${encodedUrl}/${movies[page].voteAverage}"
                        )
                    }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(localContext).data(movies[page].thumbnailURL)
                            .crossfade(true).scale(Scale.FILL).build(),
                        contentDescription = movies[page].name
                    )
                }
                Text(
                    text = movies[page].getMovieTitle(),
                    modifier = Modifier.padding(3.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}