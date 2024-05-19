package us.kikin.android.gamingbacklog.data.remote

import proto.Game

interface GamesApi {
    suspend fun getGames(
        limit: Int = 10,
        offset: Int,
    ): List<Game>
}
