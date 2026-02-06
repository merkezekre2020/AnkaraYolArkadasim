package com.ankarayolculukmusic.data.models

import com.squareup.moshi.Json

data class SpotifySearchResponse(
    @Json(name = "tracks") val tracks: TracksResponse
)

data class TracksResponse(
    @Json(name = "items") val items: List<Track>
)

data class Track(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "artists") val artists: List<Artist>,
    @Json(name = "album") val album: Album,
    @Json(name = "duration_ms") val durationMs: Int
)

data class Artist(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String
)

data class Album(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "images") val images: List<Image>
)

data class Image(
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int
)
