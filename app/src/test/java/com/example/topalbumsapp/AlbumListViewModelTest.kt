package com.example.topalbumsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.topalbumsapp.features.viewmodels.AlbumListViewModel
import com.example.topalbumsapp.models.Album
import com.example.topalbumsapp.repositories.AlbumsRepository
import com.example.topalbumsapp.utils.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class AlbumListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: AlbumsRepository
    private lateinit var viewModel: AlbumListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = AlbumListViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshAlbums sets albums when repository returns success`() = runTest {
        val fakeAlbums = listOf(
            Album("Album 1", "Artist 1", "url1", "Pop", "2025", "10 $" ,"20 feb"),
            Album("Album 2", "Artist 2", "url2", "Rock", "2024", "15 $" ,"5 jul")
        )
        whenever(repository.getAlbums()).thenReturn(Result.success(fakeAlbums))

        viewModel.refreshAlbums()
        advanceUntilIdle()

        assertEquals(fakeAlbums, viewModel.albums.getValue())
        assertEquals(false, viewModel.isLoading.getValue())
        assertNull(viewModel.error.getValue())
    }

    @Test
    fun `refreshAlbums sets error when repository returns failure`() = runTest {
        val error = IOException("Network failure")
        whenever(repository.getAlbums()).thenReturn(Result.failure(error))

        viewModel.refreshAlbums()
        advanceUntilIdle()

        val expectedMessage = ErrorHandler.getUserFriendlyMessage(error)
        assertEquals(expectedMessage, viewModel.error.getValue())
        assertEquals(false, viewModel.isLoading.getValue())
        assertEquals(emptyList<Album>(), viewModel.albums.getValue())
    }
}
