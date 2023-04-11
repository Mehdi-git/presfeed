package dev.mehdi.bakhtiari.presfeed.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mehdi.bakhtiari.presfeed.data.repository.Repository
import dev.mehdi.bakhtiari.presfeed.data.repository.RepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: RepositoryImpl): Repository

}