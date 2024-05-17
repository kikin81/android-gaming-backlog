package us.kikin.android.gamingbacklog.presentation.onboarding

sealed class OnboardingEvent {

    data object SaveAppEntry: OnboardingEvent()
}
