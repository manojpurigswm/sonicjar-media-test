package com.sonicjar.media.di

import android.content.Context
import com.sonicjar.media.data.source.BaseDataSource
import com.sonicjar.media.data.source.BaseRepository
import com.sonicjar.media.data.source.Repository
import com.sonicjar.media.data.source.file.FileDataSource
import com.sonicjar.media.data.source.file.FileFunctions
import com.sonicjar.media.data.source.remote.RemoteDataSource
import com.sonicjar.media.data.source.remote.RetrofitCalls
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideBaseRepository(
        fileDataSource: FileDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository = BaseRepository(fileDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideFileDataSource(fileFunctions: FileFunctions) : FileDataSource{
        return FileDataSource(fileFunctions)
    }

    @Singleton
    @Provides
    fun provideFileFunction(@ApplicationContext context: Context) : FileFunctions {
        return FileFunctions(context)
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(retrofitCalls: RetrofitCalls) : RemoteDataSource{
        return RemoteDataSource(retrofitCalls)
    }
}