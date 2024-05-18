package us.kikin.android.gamingbacklog.di

import android.app.Application
import com.api.igdb.request.IGDBWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import us.kikin.android.gamingbacklog.BuildConfig
import us.kikin.android.gamingbacklog.data.manager.LocalUserManagerImpl
import us.kikin.android.gamingbacklog.data.repository.GamesRepositoryImpl
import us.kikin.android.gamingbacklog.domain.manager.LocalUserManager
import us.kikin.android.gamingbacklog.domain.repository.GamesRepository
import us.kikin.android.gamingbacklog.domain.usecase.appentry.AppEntryUseCases
import us.kikin.android.gamingbacklog.domain.usecase.appentry.ReadAppEntry
import us.kikin.android.gamingbacklog.domain.usecase.appentry.SaveAppEntry
import us.kikin.android.gamingbacklog.domain.usecase.games.GamesUseCase
import us.kikin.android.gamingbacklog.domain.usecase.games.GetGames
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager),
        )

    @Provides
    @Singleton
    fun provideIGDBWrapper(): IGDBWrapper {
        IGDBWrapper.setupProxy(
            BuildConfig.SERVER_URL,
            mapOf("x-api-key" to BuildConfig.API_KEY),
        )
        return IGDBWrapper
    }

    @Provides
    @Singleton
    fun providesGamesRepository(igdbWrapper: IGDBWrapper): GamesRepository = GamesRepositoryImpl(igdbWrapper)

    @Provides
    @Singleton
    fun providesGamesUseCases(repository: GamesRepository): GamesUseCase {
        return GamesUseCase(
            getGames = GetGames(repository),
        )
    }
}
