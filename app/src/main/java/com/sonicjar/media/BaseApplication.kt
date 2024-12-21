package com.sonicjar.media

import android.app.Application
import com.sonicjar.media.data.source.BaseDataSource
import com.sonicjar.media.data.source.file.FileDataSource
import com.sonicjar.media.data.source.file.FileFunctions
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){

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