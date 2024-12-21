package com.sonicjar.media.di

import android.content.Context
import com.sonicjar.media.data.source.file.FileDataSource
import com.sonicjar.media.data.source.file.FileFunctions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Singleton
    @Provides
    fun provideFileDataSource(fileFunctions: FileFunctions) : FileDataSource{
        return FileDataSource(fileFunctions)
    }

    @Singleton
    @Provides
    fun provideFileFunction(@ApplicationContext context: Context) : FileFunctions{
        return FileFunctions(context)
    }
}