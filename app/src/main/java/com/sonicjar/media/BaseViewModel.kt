package com.sonicjar.media

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel (): ViewModel() {
    val showToast = MutableLiveData<String>()
    val showProgress = MutableLiveData<Boolean>()
}