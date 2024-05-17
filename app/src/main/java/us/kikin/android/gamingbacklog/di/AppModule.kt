package us.kikin.android.gamingbacklog.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import us.kikin.android.gamingbacklog.data.manager.LocalUserManagerImpl
import us.kikin.android.gamingbacklog.domain.manager.LocalUserManager
import us.kikin.android.gamingbacklog.domain.usecase.AppEntryUseCases
import us.kikin.android.gamingbacklog.domain.usecase.ReadAppEntry
import us.kikin.android.gamingbacklog.domain.usecase.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}
