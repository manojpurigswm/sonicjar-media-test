package com.sonicjar.media.data.source

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import javax.inject.Inject

class BaseRepository @Inject constructor(private val fileDataSource: BaseDataSource,
                                         private val remoteDataSource: BaseDataSource): Repository {
    override suspend fun getTracks(forceUpdate: Boolean): Resource<List<Track>>{
        if(forceUpdate){
            return remoteDataSource.getTracks()
        }
        else{
            return fileDataSource.getTracks()
        }
    }
}