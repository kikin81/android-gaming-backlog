package us.kikin.android.gamingbacklog.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.releaseDates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import proto.ReleaseDate
import java.util.Date

class GamesPagingSource(
    private val igdbWrapper: IGDBWrapper,
    private val afterDate: Date,
) : PagingSource<Int, ReleaseDate>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReleaseDate> {
        val offset = params.key ?: 0
        val limit = 10
        return try {
            withContext(Dispatchers.IO) {
                val games =
                    igdbWrapper.releaseDates(
                        APICalypse()
                            .fields("game.name, platform.name, human, game.cover.*")
                            .where("date > ${afterDate.time / 1000}")
                            .limit(limit)
                            .offset(offset)
                            .sort("date", Sort.ASCENDING),
                    ).distinctBy { it.id }
                LoadResult.Page(
                    data = games.distinctBy { it.id },
                    prevKey = if (offset == 0) null else offset - limit,
                    nextKey = if (games.isEmpty()) null else offset + limit,
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReleaseDate>): Int? {
        TODO("Not yet implemented")
    }
}
