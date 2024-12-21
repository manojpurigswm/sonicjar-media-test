package com.sonicjar.media.ui.home

import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.sonicjar.media.BaseViewModel
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(private val dataRepository: BaseRepository) : BaseViewModel() {

    private val _lists = MutableSharedFlow<Resource<List<Track>>>()
    val lists: SharedFlow<Resource<List<Track>>> = _lists

    fun getLists() = viewModelScope.launch {
        _lists.emit(Resource.Loading)
        _lists.emit(dataRepository.getTracks(true))
    }

}