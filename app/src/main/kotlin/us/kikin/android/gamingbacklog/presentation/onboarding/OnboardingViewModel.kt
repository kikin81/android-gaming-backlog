package us.kikin.android.gamingbacklog.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import us.kikin.android.gamingbacklog.domain.usecase.appentry.AppEntryUseCases
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel
    @Inject
    constructor(
        private val appEntryUseCases: AppEntryUseCases,
    ) : ViewModel() {
        fun onEvent(event: OnboardingEvent) {
            when (event) {
                OnboardingEvent.SaveAppEntry -> {
                    saveAppEntry()
                }
            }
        }

        private fun saveAppEntry() {
            viewModelScope.launch {
                appEntryUseCases.saveAppEntry()
            }
        }
    }
