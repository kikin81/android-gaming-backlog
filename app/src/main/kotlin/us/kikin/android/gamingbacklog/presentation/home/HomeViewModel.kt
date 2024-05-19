package us.kikin.android.gamingbacklog.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import us.kikin.android.gamingbacklog.domain.usecase.games.GamesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gamesUseCase: GamesUseCase,
) : ViewModel() {
    val games = gamesUseCase.getGames().cachedIn(viewModelScope)
}
