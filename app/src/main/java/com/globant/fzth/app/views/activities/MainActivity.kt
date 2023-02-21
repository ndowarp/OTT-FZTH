package com.globant.fzth.app.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.globant.fzth.app.theme.App
import com.globant.fzth.app.views.composables.pages.*
import com.globant.fzth.app.views.composables.pages.DetailPage
import com.globant.fzth.app.views.composables.player.VideoPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTemplate {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        LogInPage(navController)
                        //LandingPage(navController)
                    }
                    composable(
                        route = "detail/{overview}/{mediaType}/{title}/{thumbnailURL}/{voteAverage}",
                        arguments = listOf(
                            navArgument("overview") {type = NavType.StringType},
                            navArgument("mediaType") {type = NavType.StringType},
                            navArgument("title") {type = NavType.StringType},
                            navArgument("thumbnailURL") {type = NavType.StringType},
                            navArgument("voteAverage") {type = NavType.FloatType}
                        )
                    ) { backStackEntry ->
                        val overview = backStackEntry.arguments?.getString("overview")
                        val mediaType = backStackEntry.arguments?.getString("mediaType")
                        val title = backStackEntry.arguments?.getString("title")
                        val thumbnailURL = backStackEntry.arguments?.getString("thumbnailURL")
                        val voteAverage = backStackEntry.arguments?.getFloat("voteAverage")

                        DetailPage(navController, overview, mediaType, title, thumbnailURL, voteAverage )
                    }
                    composable(
                        route = "search"
                    ) {
                        SearchBar()
                    }
                    composable(
                        route = "log_in"
                    ) {
                        LogInPage(navController)
                    }
                    composable(
                        route = "create_account"
                    ) {
                        CreateAccountPage(navController)
                    }
                    composable(
                        route = "landing_page"
                    ) {
                        LandingPage(navController)
                    }
                    composable(
                        route = "purchase_plan"
                    ) {
                        PurchasePlanPage(navController)
                    }
                    composable(
                        route = "video_player"
                    ) {
                        VideoPlayer()
                    }
                }
            }
        }
    }
}


@Composable
private fun AppTemplate(content: @Composable () -> Unit) {
    App {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
