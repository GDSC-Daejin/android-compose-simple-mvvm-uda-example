package app.harry.udaexample.di

import app.harry.udaexample.data.NounRepositoryImp
import app.harry.udaexample.data.VerbRepositoryImp
import app.harry.udaexample.domain.repo.NounRepository
import app.harry.udaexample.domain.repo.VerbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNounRepository(): NounRepository {
        return NounRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideVerbRepository(): VerbRepository {
        return VerbRepositoryImp()
    }

}