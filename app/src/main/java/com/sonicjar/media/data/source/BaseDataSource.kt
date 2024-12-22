package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track

interface BaseDataSource {
    suspend fun getTracks(): Resource<List<Track>>
}