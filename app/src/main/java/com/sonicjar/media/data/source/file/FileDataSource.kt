package com.sonicjar.media.data.source.file

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.BaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class FileDataSource @Inject constructor(private val fileFunctions: FileFunctions): BaseDataSource {
    override suspend fun getTracks(): Resource<List<Track>>{
        try {
            val response = fileFunctions.readTracksFromFile()
            return Resource.Success(response)
        }
        catch (e: Exception){
            return Resource.Fail(e)
        }
    }
}