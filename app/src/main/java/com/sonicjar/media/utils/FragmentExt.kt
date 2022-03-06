package com.sonicjar.media.utils

import androidx.fragment.app.Fragment
import com.sonicjar.media.BaseApplication
import com.sonicjar.media.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireActivity().applicationContext as BaseApplication).getRepository
    val application = (requireActivity().applicationContext as BaseApplication).getApplication
    return ViewModelFactory(repository, this)
}