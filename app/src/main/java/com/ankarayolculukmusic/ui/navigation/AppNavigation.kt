package com.ankarayolculukmusic.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankarayolculukmusic.ui.screens.HomeScreen
import com.ankarayolculukmusic.ui.screens.TrackDetailScreen
import com.ankarayolculukmusic.ui.viewmodels.MusicViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: MusicViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onTrackClick = { trackId ->
                    navController.navigate("${Screen.TrackDetail.route}/$trackId")
                }
            )
        }
        composable("${Screen.TrackDetail.route}/{trackId}") { backStackEntry ->
            val trackId = backStackEntry.arguments?.getString("trackId")
            val track = viewModel.tracksState.value.let { state ->
                when (state) {
                    is com.ankarayolculukmusic.ui.viewmodels.TracksState.Success -> {
                        state.tracks.find { it.id == trackId }
                    }
                    else -> null
                }
            }
            TrackDetailScreen(
                track = track,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object TrackDetail : Screen("track_detail")
}
