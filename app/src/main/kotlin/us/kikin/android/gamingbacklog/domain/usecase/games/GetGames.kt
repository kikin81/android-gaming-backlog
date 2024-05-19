package us.kikin.android.gamingbacklog.domain.usecase.games

import us.kikin.android.gamingbacklog.domain.repository.GamesRepository
import java.util.Date

class GetGames(
    private val gamesRepository: GamesRepository,
) {
    operator fun invoke(afterDate: Date) = gamesRepository.getGames(afterDate = afterDate)
}
