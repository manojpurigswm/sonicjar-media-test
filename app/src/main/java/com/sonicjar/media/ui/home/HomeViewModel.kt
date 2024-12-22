package com.sonicjar.media.ui.home

import androidx.lifecycle.*
import com.sonicjar.media.BaseViewModel
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(private val dataRepository: Repository) : BaseViewModel() {

    private val _lists = MutableSharedFlow<Resource<List<Track>>>()
    val lists: SharedFlow<Resource<List<Track>>> = _lists

    fun getLists() = viewModelScope.launch{
        _lists.emit(dataRepository.getTracks(true))
    }

}