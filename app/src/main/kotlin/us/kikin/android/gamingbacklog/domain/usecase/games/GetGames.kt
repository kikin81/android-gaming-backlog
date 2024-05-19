package us.kikin.android.gamingbacklog.domain.usecase.games

import us.kikin.android.gamingbacklog.domain.repository.GamesRepository

class GetGames(
    private val gamesRepository: GamesRepository,
) {
    operator fun invoke() = gamesRepository.getGames()
}
