package us.kikin.android.gamingbacklog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.api.igdb.request.IGDBWrapper
import kotlinx.coroutines.flow.Flow
import proto.Game
import us.kikin.android.gamingbacklog.data.remote.GamesPagingSource
import us.kikin.android.gamingbacklog.domain.repository.GamesRepository

class GamesRepositoryImpl(
    private val igdbWrapper: IGDBWrapper,
) : GamesRepository {
    override fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GamesPagingSource(
                    igdbWrapper = igdbWrapper,
                )
            },
        ).flow
    }
}
