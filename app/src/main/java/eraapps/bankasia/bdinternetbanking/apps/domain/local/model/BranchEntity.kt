package eraapps.bankasia.bdinternetbanking.apps.domain.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "Branch_table", indices = arrayOf(
        Index(
            value = ["sl", "branchCode"],
            unique = true
        )
    )
)
data class BranchEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sl")
    @SerializedName("sl")
    var sl: Int = 0,

    @ColumnInfo(name = "branchCode")
    @SerializedName("branchCode")
    var branchCode: String = "",

    @ColumnInfo(name = "branchName")
    @SerializedName("branchName")
    var branchName: String = "",

    @ColumnInfo(name = "branchAddress")
    @SerializedName("branchAddress")
    var branchAddress: String = "",

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    var phone: String = "",

    @ColumnInfo(name = "fax")
    @SerializedName("fax")
    var fax: String = "",

    @ColumnInfo(name = "logitude")
    @SerializedName("logitude")
    var logitude: String = "",

    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    var latitude: String = "",

    @ColumnInfo(name = "opendate")
    @SerializedName("opendate")
    var opendate: String = ""
)