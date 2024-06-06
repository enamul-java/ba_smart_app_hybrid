package eraapps.bankasia.bdinternetbanking.apps.domain.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "Options_table", indices = arrayOf(
        Index(
            value = ["id", "code"],
            unique = true
        )
    )
)
data class OptionsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "code")
    @SerializedName("code")
    var code: String = "",

    @ColumnInfo(name = "codeDes")
    @SerializedName("codeDes")
    var codeDes: String = "",

    @ColumnInfo(name = "hardCode")
    @SerializedName("hardCode")
    var hardCode: String = "",

    @ColumnInfo(name = "actFlg")
    @SerializedName("actFlg")
    var actFlg: String = "",

    @ColumnInfo(name = "createDate")
    @SerializedName("createDate")
    var createDate: String = ""

)