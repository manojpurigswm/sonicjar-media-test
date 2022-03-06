package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getTracks(forceUpdate: Boolean = false): Flow<Resource<List<Track>>>


    suspend fun getTracks(forceUpdate: Boolean = false, query: String = ""): Flow<Resource<List<Track>>>

    suspend fun getTracksCount(forceUpdate: Boolean = false): Flow<Resource<Int>>
}