package us.kikin.android.gamingbacklog.domain.usecase.appentry

import kotlinx.coroutines.flow.Flow
import us.kikin.android.gamingbacklog.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager,
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}
