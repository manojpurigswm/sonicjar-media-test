package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getTracks(forceUpdate: Boolean = false): Resource<List<Track>>
}