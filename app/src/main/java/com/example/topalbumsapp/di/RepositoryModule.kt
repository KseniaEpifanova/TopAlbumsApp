package com.example.topalbumsapp.di

import com.example.topalbumsapp.repositories.AlbumMapper
import com.example.topalbumsapp.repositories.AlbumsRepository
import com.example.topalbumsapp.repositories.AlbumsRepositoryImpl
import com.example.topalbumsapp.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumsRepository(
        apiService: ApiService,
        mapper: AlbumMapper
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(apiService, mapper)
    }
}
