package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BranchLocation

@Entity(
    //bl = Branch Location
    tableName = "atml_table", indices = arrayOf(
        Index(
            value = ["slNo"],
            unique = true
        )
    )
)
data class ATMLocationDto(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "slNo")
    @SerializedName("slNo")
    var slNo: Int = 0,

    @SerializedName("atmLocation")
    val atmLocation: String,

    @SerializedName("atmLongitude")
    val atmLongitude: String,

    @SerializedName("atmLatitude")
    val atmLatitude: String,

    @SerializedName("atmLocationCode")
    val atmLocationCode: String,

    @SerializedName("atmName")
    val atmName: String
)


/*
private Int slNo = 0;
    String atmLocation = "";
    String atmLongitude = "";
    String atmLatitude = "";
    String atmLocationCode = "";
    String atmName = "";
 */