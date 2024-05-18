package us.kikin.android.gamingbacklog.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import proto.Game

interface GamesRepository {
    fun getGames(): Flow<PagingData<Game>>
}
