package us.kikin.android.gamingbacklog.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import proto.ReleaseDate
import java.util.Date

interface GamesRepository {
    fun getGames(afterDate: Date): Flow<PagingData<ReleaseDate>>
}
