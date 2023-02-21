package com.globant.fzth.app.views.composables.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.globant.fzth.app.events.LandingEvents
import com.globant.fzth.app.viewmodel.LandingViewModel
import com.globant.fzth.app.views.composables.indicators.LoadingIndicator
import com.globant.fzth.app.views.composables.poster.LandingPoster
import com.globant.fzth.app.views.composables.sections.GenderSection
import com.globant.fzth.domain.models.Movie

@Composable
fun LandingPage(
    navController: NavController,
    viewModel: LandingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var showInitialLoading by remember { mutableStateOf(true) }


    LaunchedEffect(key1 = state.isLoading) {
        showInitialLoading = state.isLoading
    }

    if (showInitialLoading) {
        LoadingIndicator()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "TMDB App") },
                    actions = {
                        IconButton(onClick = { navController.navigate("search") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (state.trendingMovies.isNotEmpty()) {
                    LandingPoster(movie = state.trendingMovies.random(), navController)
                }
                GenderSection(
                    navController = navController,
                    title = "Trending",
                    movies = state.trendingMovies
                )
                GenderSection(
                    navController = navController,
                    title = "Top rated",
                    movies = state.topRatedMovie
                )
                GenderSection(
                    navController = navController,
                    title = "Action",
                    movies = state.actionMovies
                )
                GenderSection(
                    navController = navController,
                    title = "Romance",
                    movies = state.romanceMovies
                )
                GenderSection(
                    navController = navController,
                    title = "Comedy",
                    movies = state.comedyMovies
                )
                GenderSection(
                    navController = navController,
                    title = "Horror",
                    movies = state.horrorMovies
                )
                GenderSection(
                    navController = navController,
                    title = "Documentary",
                    movies = state.documentariesMovies
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    viewModel: LandingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var searchText by remember { mutableStateOf("") }
    var searchDone = false


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = searchText,
            onValueChange = {
                searchText = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.onEvent(event = LandingEvents.OnSearch(searchText))
                },
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.Red,
                cursorColor = Color.Red,
            ),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Adaptive(120.dp)
        ) {
            items(state.searchResult.size) { index ->
                MediaListItem(state.searchResult[index], Modifier.padding(2.dp))
                searchDone = true
            }
        }
        if (searchDone) {
            state.searchResult = emptyList()
            searchDone = false
        }
    }
}


@Composable
fun MediaListItem(item: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnailURL)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(92.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp)
        ) {
            Text(
                text = item.title.toString(),
                style = MaterialTheme.typography.body2,
                maxLines = 2
            )
        }
    }
}

