package com.sonicjar.media

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonicjar.media.data.source.Repository

abstract class BaseViewModel (val dataRepository: Repository): ViewModel() {
    val showToast = MutableLiveData<String>()
    val showProgress = MutableLiveData<Boolean>()
}