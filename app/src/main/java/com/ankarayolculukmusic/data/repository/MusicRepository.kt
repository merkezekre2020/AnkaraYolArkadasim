package com.ankarayolculukmusic.data.repository

import com.ankarayolculukmusic.data.api.ApiClient
import com.ankarayolculukmusic.data.models.Track
import com.ankarayolculukmusic.data.models.SpotifySearchResponse

class MusicRepository {

    private val apiService = ApiClient.apiService

    suspend fun searchTracks(query: String): Result<List<Track>> {
        return try {
            val response = apiService.searchTracks(
                accessToken = "dummy_token", // This will be replaced by real token
                query = query
            )
            if (response.isSuccessful) {
                val tracks = response.body()?.tracks?.items ?: emptyList()
                Result.success(tracks)
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Categorized playlist queries
    fun getPlaylistQueries(): Map<String, String> {
        return mapOf(
            "Ankara Havaları" to "Ankara",
            "Gece Sürüşü - Deep House" to "deep house",
            "Yol Hikayeleri - Akustik" to "akustik",
            "Bozkır Müzikleri" to "bozkır"
        )
    }
}
