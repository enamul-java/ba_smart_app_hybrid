package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BranchLocation

@Entity(
    //bl = Branch Location
    tableName = "bl_table", indices = arrayOf(
        Index(
            value = ["sl", "branchCode"],
            unique = true
        )
    )
)
data class BranchLocationDto(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sl")
    @SerializedName("sl")
    var sl: Int = 0,

    @SerializedName("branchCode")
    val branchCode: String,

    @SerializedName("branchName")
    val branchName: String,

    @SerializedName("address")
    val branchAddress: String,

    @SerializedName("contactNumber")
    val phone: String,

    @SerializedName("fax")
    val fax: String,

    @SerializedName("lat")
    val logitude: String,

    @SerializedName("lng")
    val latitude: String,

    @SerializedName("openDate")
    val opendate: String
)

/*
private int sl = 0;
    private String branchCode = "";
    private String branchName = "";
    private String address = "";
    private String contactNumber = "";
    private String fax = "";
    private String lat = "";
    private String lng = "";
    private String openDate = "";
 */

/*
* bind only required data to value from the api response. Remove extra data from full repose
* */

fun BranchLocationDto.getBrnachLocation(): BranchLocation {

    return BranchLocation(
        sl = "$sl",
        branchCode = branchCode,
        branchName = branchName,
        branchAddress = branchAddress,
        phone = phone,
        fax = fax,
        logitude = logitude,
        latitude = latitude,
        opendate = opendate
    )
}