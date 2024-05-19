package us.kikin.android.gamingbacklog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.api.igdb.request.IGDBWrapper
import kotlinx.coroutines.flow.Flow
import proto.ReleaseDate
import us.kikin.android.gamingbacklog.data.remote.GamesPagingSource
import us.kikin.android.gamingbacklog.domain.repository.GamesRepository
import java.util.Date

class GamesRepositoryImpl(
    private val igdbWrapper: IGDBWrapper,
) : GamesRepository {
    override fun getGames(afterDate: Date): Flow<PagingData<ReleaseDate>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GamesPagingSource(
                    igdbWrapper = igdbWrapper,
                    afterDate = afterDate,
                )
            },
        ).flow
    }
}
