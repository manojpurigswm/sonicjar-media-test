package com.sonicjar.media.data.source.file

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.BaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FileDataSource @Inject constructor(private val fileFunctions: FileFunctions, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): BaseDataSource {
    override suspend fun getTracks(): Resource<List<Track>> = withContext(ioDispatcher) {
        try {
            val response = fileFunctions.readTracksFromFile()
            return@withContext Resource.Success(response)
        }
        catch (e: Exception){
            return@withContext Resource.Fail(e)
        }
    }
}