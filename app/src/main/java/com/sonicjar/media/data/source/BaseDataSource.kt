package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import kotlinx.coroutines.flow.Flow

interface BaseDataSource {
    suspend fun getTracks(): Flow<Resource<List<Track>>>


    suspend fun getTracks(query: String = ""): Flow<Resource<List<Track>>>

    suspend fun getTracksCount(): Flow<Resource<Int>>
}