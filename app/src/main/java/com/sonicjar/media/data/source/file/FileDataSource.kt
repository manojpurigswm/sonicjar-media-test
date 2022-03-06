package com.sonicjar.media.data.source.file

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.BaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception

class FileDataSource internal constructor(
    private val fileFunctions: FileFunctions,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
): BaseDataSource {
    override suspend fun getTracks(): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading)
        val resource = try {
            val response = fileFunctions.readTracksFromFile()
            Resource.Success(response)
        }
        catch (e: Exception){
            Resource.Fail(e)
        }
        emit(resource)
    }.flowOn(ioDispatcher)

    override suspend fun getTracks(query: String): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading)
        val resource = try {
            val response = if(query.isNullOrEmpty()) fileFunctions.readTracksFromFile().sortedBy { it.trackID } else searchQuery(query, fileFunctions.readTracksFromFile())
            if(response.isNullOrEmpty()) Resource.Fail(Exception()) else Resource.Success(response)
        }
        catch (e: Exception){
            Resource.Fail(e)
        }
        emit(resource)
    }.flowOn(ioDispatcher)

    override suspend fun getTracksCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading)
        val resource = try {
            val response = fileFunctions.readTracksFromFile()
            Resource.Success(response.size)
        }
        catch (e: Exception){
            Resource.Fail(e)
        }
        emit(resource)
    }.flowOn(ioDispatcher)

    fun searchQuery(query: String, list: List<Track>): List<Track>{
        val queryList = query.split(" ").toMutableList()
        val arrayList = mutableSetOf<Track>()
        var orList = list

        while (queryList.size > 0){
            val (match, rest) = orList.partition { it.trackTitle.split(" ").containsAll(queryList) && it.trackSubtitle.split(" ").containsAll(queryList)}
            arrayList.addAll(match)
            orList = rest

            val (match1, rest1) = orList.partition { it.trackTitle.split(" ").containsAll(queryList)}
            arrayList.addAll(match1)
            orList = rest1

            val (match2, rest2) = orList.partition { it.trackSubtitle.split(" ").containsAll(queryList)}
            arrayList.addAll(match2)
            orList = rest2

            val (match3, rest3) = orList.partition { it.trackID.toString().split(" ").containsAll(queryList)}
            arrayList.addAll(match3)
            orList = rest3

            queryList.removeAt(queryList.size - 1)
        }
        return arrayList.distinct()
    }
}