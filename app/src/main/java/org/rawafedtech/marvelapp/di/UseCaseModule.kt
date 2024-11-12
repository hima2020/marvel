package org.rawafedtech.marvelapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.rawafedtech.marvelapp.data.interceptor.GetComicsUseCaseImpl
import org.rawafedtech.marvelapp.data.interceptor.GetEventsUseCaseImpl
import org.rawafedtech.marvelapp.data.interceptor.GetMarvelsUseCaseImpl
import org.rawafedtech.marvelapp.data.interceptor.GetSeriesUseCaseImpl
import org.rawafedtech.marvelapp.data.interceptor.GetStoriesUseCaseImpl
import org.rawafedtech.marvelapp.domain.interceptor.GetComicsUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetEventsUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetMarvelsUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetSeriesUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetStoriesUseCase
import org.rawafedtech.marvelapp.domain.repo.MarvelRepository


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideMarvelsUseCase(marvelsRepo: MarvelRepository): GetMarvelsUseCase {
        return GetMarvelsUseCaseImpl(marvelsRepo)
    }

    @Provides
    fun provideComicsUseCase(marvelsRepo: MarvelRepository): GetComicsUseCase {
        return GetComicsUseCaseImpl(marvelsRepo)
    }

    @Provides
    fun provideEventsUseCase(marvelsRepo: MarvelRepository): GetEventsUseCase {
        return GetEventsUseCaseImpl(marvelsRepo)
    }

    @Provides
    fun provideSeriesUseCase(marvelsRepo: MarvelRepository): GetSeriesUseCase {
        return GetSeriesUseCaseImpl(marvelsRepo)
    }

    @Provides
    fun provideStoriesUseCase(marvelsRepo: MarvelRepository): GetStoriesUseCase {
        return GetStoriesUseCaseImpl(marvelsRepo)
    }

}