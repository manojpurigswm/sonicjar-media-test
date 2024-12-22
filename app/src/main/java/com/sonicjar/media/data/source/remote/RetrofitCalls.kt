package com.sonicjar.media.data.source.remote

import com.sonicjar.media.data.Track
import retrofit2.Response
import retrofit2.http.POST

interface RetrofitCalls {
    @POST("tracks.json")
    suspend fun getTracks(): Response<List<Track>>
}