package com.sonicjar.media.utils

import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.Repository

class FakeRepository : Repository {
    companion object{
        val data = listOf(Track())
        val response = Resource.Success(data)
    }
    override suspend fun getTracks(forceUpdate: Boolean): Resource<List<Track>> {
        return Resource.Success(data)
    }
}