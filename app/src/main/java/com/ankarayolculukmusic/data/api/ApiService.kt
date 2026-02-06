package com.ankarayolculukmusic.data.api

import com.ankarayolculukmusic.data.models.SpotifySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("v1/search")
    suspend fun searchTracks(
        @Header("Authorization") accessToken: String,
        @Query("q") query: String,
        @Query("type") type: String = "track",
        @Query("limit") limit: Int = 20
    ): Response<SpotifySearchResponse>
}
