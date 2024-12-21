package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.file.FileDataSource
import com.sonicjar.media.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BaseRepository @Inject constructor(private val fileDataSource: FileDataSource, private val remoteDataSource: RemoteDataSource): Repository {
    override suspend fun getTracks(forceUpdate: Boolean): Resource<List<Track>> {
        if(forceUpdate){
            // here we update network data
            return remoteDataSource.getTracks()
        }
        return fileDataSource.getTracks()
    }
}