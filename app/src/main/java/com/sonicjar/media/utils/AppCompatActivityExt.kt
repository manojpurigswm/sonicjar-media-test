package com.sonicjar.media.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sonicjar.media.BaseApplication
import com.sonicjar.media.ViewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun AppCompatActivity.getViewModelFactory(): ViewModelFactory {
    val repository = (applicationContext as BaseApplication).getRepository
    return ViewModelFactory(repository, this)
}

inline fun <T> AppCompatActivity.bind(
    source: Flow<T>,
    crossinline action: (T) -> Unit
) {
    source.onEach { action.invoke(it) }
        .launchIn(lifecycleScope)
}