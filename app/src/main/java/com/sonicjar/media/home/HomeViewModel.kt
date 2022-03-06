package com.sonicjar.media.home

import androidx.lifecycle.*
import com.sonicjar.media.BaseViewModel
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.source.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class HomeViewModel(dataRepository: Repository) : BaseViewModel(dataRepository) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: Flow<String> = _searchQuery

    private val loadTracks = MutableSharedFlow<Unit>()
    private val loadCount = MutableSharedFlow<Unit>()

    private val resource = loadTracks
        .map { _searchQuery.value }
        .flatMapLatest { dataRepository.getTracks(false, it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading)

    private val countSource = loadCount
        .map {  }
        .flatMapLatest { dataRepository.getTracksCount(false) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading)

    val isLoading = resource.map {
        it.isLoading
    }
    val isFail = resource.map {
        it.isFail
    }
    val data = resource.map {
        it.valueOrNull.orEmpty()
    }

    val count = countSource.map {
        it.valueOrNull?:0
    }

    init {
        viewModelScope.launch {
            loadTracks.emit(Unit)
            loadCount.emit(Unit)
        }
    }

    fun setQuery(query: String) {
        if(_searchQuery.value != query){
            _searchQuery.value = query
        }
    }

    fun submit() {
        viewModelScope.launch {
            loadTracks.emit(Unit)
        }
    }

}