package com.sonicjar.media.ui.home

import androidx.lifecycle.*
import com.sonicjar.media.BaseViewModel
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(private val dataRepository: Repository) : BaseViewModel() {

    private val _lists = MutableStateFlow<Resource<List<Track>>>(Resource.Loading)
    val lists: StateFlow<Resource<List<Track>>> = _lists

    init {
        getLists()
    }

    fun getLists() = viewModelScope.launch(Dispatchers.IO){
        _lists.emit(Resource.Loading)
        _lists.emit(dataRepository.getTracks(true))
    }
}