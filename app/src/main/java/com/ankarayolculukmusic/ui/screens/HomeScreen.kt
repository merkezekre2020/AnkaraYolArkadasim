package com.ankarayolculukmusic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ankarayolculukmusic.R
import com.ankarayolculukmusic.ui.components.TrackCard
import com.ankarayolculukmusic.ui.viewmodels.MusicViewModel
import com.ankarayolculukmusic.ui.viewmodels.TracksState

@Composable
fun HomeScreen(
    viewModel: MusicViewModel = viewModel(),
    onTrackClick: (trackId: String) -> Unit
) {
    val tracksState by viewModel.tracksState.collectAsState()
    val playlistQueries by remember { mutableStateOf(viewModel.playlistQueries) }
    val selectedCategory = remember { mutableStateOf(playlistQueries.keys.first()) }

    LaunchedEffect(selectedCategory.value) {
        viewModel.fetchTracks(selectedCategory.value)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.app_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Playlist Categories
        Text(
            text = "Playlists",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            playlistQueries.keys.forEach { category ->
                Chip(
                    selected = selectedCategory.value == category,
                    onClick = { selectedCategory.value = category },
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Tracks List
        when (tracksState) {
            is TracksState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.loading),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            is TracksState.Success -> {
                val tracks = (tracksState as TracksState.Success).tracks
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(tracks) { track ->
                        TrackCard(
                            track = track,
                            onClick = { onTrackClick(track.id) }
                        )
                    }
                }
            }
            is TracksState.Error -> {
                val error = (tracksState as TracksState.Error).exception
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.error),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = error.message ?: "Unknown error",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.fetchTracks(selectedCategory.value) }
                        ) {
                            Text(text = stringResource(R.string.retry))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            content()
        }
    }
}
