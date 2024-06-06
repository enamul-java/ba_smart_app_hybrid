package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto

@Dao
interface ATMLocationDao {
    //atml = Branch Location
    @Query("SELECT * FROM atml_table")
    fun getAtmLocations():List<ATMLocationDto>

    @Query("SELECT count(*) FROM atml_table")
    fun atmLocationCount():Int

    @Query("DELETE FROM atml_table")
    fun atmLocationDelete()

    //Replace use for insert & update same function
    //suspend used and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAtmLocation(atmLocationEntity: ATMLocationDto):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAtmLocation(atmLocationEntitys: List<ATMLocationDto>)

}