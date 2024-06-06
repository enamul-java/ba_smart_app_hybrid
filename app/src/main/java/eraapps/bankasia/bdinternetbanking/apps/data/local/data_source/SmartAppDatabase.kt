package eraapps.bankasia.bdinternetbanking.apps.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local.BranchLocationDao
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.*


@Database(
    entities = [
        BranchEntity::class,
        OptionsEntity::class,
        UserIdEntity::class,
        ContactRecentEntity::class,
        UserFingerEntity::class,
        RecentTransferEntity::class,
    ],
    version = 24,
    exportSchema = true
)
abstract class SmartAppDatabase : RoomDatabase() {

    abstract val smartAppDao: SmartAppDao

    companion object {
        const val DATABASE_NAME = "smartappdb.db"
    }
}