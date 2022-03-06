package com.sonicjar.media

import android.app.Application
import com.sonicjar.media.data.source.BaseDataSource
import com.sonicjar.media.data.source.BaseRepository
import com.sonicjar.media.data.source.Repository
import com.sonicjar.media.data.source.file.FileDataSource
import com.sonicjar.media.data.source.file.FileFunctions

class BaseApplication : Application(){

    private val fileDataSource: BaseDataSource
        get() = FileDataSource(FileFunctions(applicationContext))

    val getRepository: Repository
        get() = BaseRepository(fileDataSource)

    val getApplication: Application
        get() = this

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        if (singleton == null)
            singleton = this
    }

    companion object {
        var singleton: BaseApplication? = null
    }
}