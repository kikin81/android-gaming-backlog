package us.kikin.android.gamingbacklog.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.games
import proto.Game

class GamesPagingSource(
    private val igdbWrapper: IGDBWrapper,
) : PagingSource<Int, Game>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val offset = params.key ?: 0
        val limit = 10
        return try {
            val games =
                igdbWrapper.games(
                    APICalypse()
                        .fields("*")
                        .limit(limit)
                        .offset(offset)
                        .sort("release_dates.date", Sort.ASCENDING),
                ).distinctBy { it.id }
            LoadResult.Page(
                data = games,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (games.isEmpty()) null else offset + limit,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        TODO("Not yet implemented")
    }
}
