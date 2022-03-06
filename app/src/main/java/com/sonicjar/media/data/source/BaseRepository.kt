package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import kotlinx.coroutines.flow.Flow

class BaseRepository internal constructor(
    private val fileDataSource: BaseDataSource
): Repository {
    override suspend fun getTracks(forceUpdate: Boolean): Flow<Resource<List<Track>>> {
        if(forceUpdate){
            // here we update network data in local db
        }
        return fileDataSource.getTracks()
    }

    override suspend fun getTracks(
        forceUpdate: Boolean,
        query: String
    ): Flow<Resource<List<Track>>> {
        if(forceUpdate){
            // here we update network data in local db
        }
        return fileDataSource.getTracks(query)
    }

    override suspend fun getTracksCount(forceUpdate: Boolean): Flow<Resource<Int>> {
        if(forceUpdate){
            // here we update network data in local db
        }
        return fileDataSource.getTracksCount()
    }
}