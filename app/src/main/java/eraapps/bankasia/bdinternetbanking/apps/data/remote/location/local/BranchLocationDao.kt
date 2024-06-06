package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto

@Dao
interface BranchLocationDao {
    //bl = Branch Location
    @Query("SELECT * FROM bl_table")
    fun getBranchLocations():List<BranchLocationDto>

    @Query("SELECT count(*) FROM bl_table")
    fun branchLocationCount():Int

    @Query("DELETE FROM bl_table")
    fun branchLocationDelete()

    //Replace use for insert & update same function
    //suspend used and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBranchLocation(branchLocationEntity: BranchLocationDto):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBranchLocation(branchLocationEntitys: List<BranchLocationDto>)

}