package com.example.topalbumsapp.features.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topalbumsapp.models.Album
import com.example.topalbumsapp.repositories.AlbumsRepository
import com.example.topalbumsapp.utils.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>(emptyList())
    val albums: LiveData<List<Album>> = _albums

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        refreshAlbums()
    }

    fun refreshAlbums() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = withContext(Dispatchers.IO) {
                repository.getAlbums()
            }

            _isLoading.value = false

            result.fold(
                onSuccess = { albumList ->
                    _albums.value = albumList
                },
                onFailure = { throwable ->
                    _error.value = ErrorHandler.getUserFriendlyMessage(throwable)
                }
            )
        }
    }

    fun clearError() {
        _error.value = null
    }
}
