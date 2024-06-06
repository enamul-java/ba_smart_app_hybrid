package eraapps.bankasia.bdinternetbanking.apps.data.local.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.*

@Dao
interface SmartAppDao {

    @Query("SELECT * FROM Branch_table")
    fun getBranchLocations(): List<BranchEntity>

    @Query("SELECT count(*) FROM Branch_table")
    fun branchLocationCount(): Int

    //Replace use for insert & update same function
    //suspend used and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBranchLocaition(branchLocationEntity: BranchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOption(optionsModel: OptionsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserId(userModel: UserIdEntity)

    @Query("DELETE FROM user_table WHERE userid=:userid")
    suspend fun deleteUserId(userid: String)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUserId()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFingerInfo(userModel: UserFingerEntity)

    @Update
    suspend fun updateUserFingerInfo(userModel: UserFingerEntity)

    @Query("SELECT * FROM Options_table WHERE hardCode='LAN' AND actFlg='Y'")
    fun readLanguage(): List<OptionsEntity>

    @Query("SELECT * FROM user_table")
    fun getUser(): List<UserIdEntity>

    @Update
    suspend fun updateLanguage(optionsModel: OptionsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contactModel: ContactRecentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentTransfer(recentTransferEntity: RecentTransferEntity)

    @Query("SELECT * FROM contact_table")
    fun getContactList(): List<ContactRecentEntity>

    @Query("SELECT count(phone) FROM contact_table WHERE phone=:phone")
    fun checkExistionContact(phone: String): Int

    @Query("SELECT * FROM user_finger_table WHERE userid=:userid")
    fun getUserFingerInfo(userid: String): UserFingerEntity

    @Query("SELECT * FROM user_table WHERE userid=:userid")
    fun userExist(userid: String): UserIdEntity

    @Query("SELECT * FROM recent_transfer_table WHERE type=:type and sourceAc=:sourceAc")
    fun getrecentTransferList(type: String, sourceAc: String): List<RecentTransferEntity>

}