package com.sonicjar.media.data.source.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sonicjar.media.data.Track

class FileFunctions(private val context: Context) {
    suspend fun readTracksFromFile(): List<Track> {
        val gson = Gson()
        val itemType = object : TypeToken<List<Track>>() {}.type
        return gson.fromJson(context.assets.readAssetsFile("tracks.json"), itemType)
    }
}