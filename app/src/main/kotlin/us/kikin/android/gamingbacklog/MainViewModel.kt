package us.kikin.android.gamingbacklog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import us.kikin.android.gamingbacklog.domain.usecase.appentry.AppEntryUseCases
import us.kikin.android.gamingbacklog.presentation.navigation.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val appEntryUseCases: AppEntryUseCases,
    ) : ViewModel() {
        var splashCondition by mutableStateOf(true)
            private set

        var startDestination by mutableStateOf(Route.AppStartNavigation.route)
            private set

        init {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                startDestination =
                    if (shouldStartFromHomeScreen) {
                        Route.GamesNavigation.route
                    } else {
                        Route.AppStartNavigation.route
                    }
                delay(300)
                splashCondition = false
            }.launchIn(viewModelScope)
        }
    }
