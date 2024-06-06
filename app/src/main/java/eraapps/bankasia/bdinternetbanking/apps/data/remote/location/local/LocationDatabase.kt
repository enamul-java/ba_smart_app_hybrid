package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local

import androidx.room.Database
import androidx.room.RoomDatabase
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto


@Database(
    entities = [BranchLocationDto::class, ATMLocationDto::class],
    version = 5,
    exportSchema = false
)
abstract class LocationDatabase : RoomDatabase() {

    abstract val branchLocationDao: BranchLocationDao
    abstract val aTMLocationDao: ATMLocationDao

    companion object {
        const val DATABASE_NAME = "locaiton.db"
    }
}