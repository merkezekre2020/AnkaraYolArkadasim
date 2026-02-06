package com.ankarayolculukmusic.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankarayolculukmusic.data.models.Track
import com.ankarayolculukmusic.data.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {
    private val repository = MusicRepository()

    // State for tracks with loading and error handling
    private val _tracksState = MutableStateFlow<TracksState>(TracksState.Loading)
    val tracksState: StateFlow<TracksState> = _tracksState.asStateFlow()

    // State for selected track
    private val _selectedTrack = MutableStateFlow<Track?>(null)
    val selectedTrack: StateFlow<Track?> = _selectedTrack.asStateFlow()

    // Get playlist queries
    val playlistQueries = repository.getPlaylistQueries()

    // Fetch tracks for a specific category
    fun fetchTracks(category: String) {
        viewModelScope.launch {
            _tracksState.value = TracksState.Loading
            val query = playlistQueries[category] ?: category
            val result = repository.searchTracks(query)
            _tracksState.value = when (result) {
                is Result.Success -> TracksState.Success(result.value)
                is Result.Failure -> TracksState.Error(result.exception)
            }
        }
    }

    // Select a track
    fun selectTrack(track: Track) {
        _selectedTrack.value = track
    }

    // Clear selected track
    fun clearSelectedTrack() {
        _selectedTrack.value = null
    }
}

// Sealed class for tracks state
sealed class TracksState {
    object Loading : TracksState()
    data class Success(val tracks: List<Track>) : TracksState()
    data class Error(val exception: Throwable) : TracksState()
}
