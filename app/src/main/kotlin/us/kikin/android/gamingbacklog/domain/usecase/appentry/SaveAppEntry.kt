package us.kikin.android.gamingbacklog.domain.usecase.appentry

import us.kikin.android.gamingbacklog.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
