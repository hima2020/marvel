package org.rawafedtech.marvelapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.rawafedtech.marvelapp.data.api.ApiService
import org.rawafedtech.marvelapp.data.repo.MarvelsRepositoryImpl
import org.rawafedtech.marvelapp.domain.repo.MarvelRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    @Provides
    fun provideMarvelRepository(
        apiService: ApiService
    ): MarvelRepository {
        return MarvelsRepositoryImpl(apiService)
    }
}