package eraapps.bankasia.bdinternetbanking.apps.domain.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "user_finger_table", indices = arrayOf(
        Index(
            value = ["id", "userid"],
            unique = true
        )
    )
)
data class UserFingerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "userid")
    @SerializedName("userid")
    var userid: String? = "",

    @ColumnInfo(name = "fingerStatus")
    @SerializedName("fingerStatus")
    var fingerStatus: String? = "",

    @ColumnInfo(name = "password")
    @SerializedName("password")
    var password: String? = "",

    @ColumnInfo(name = "expiryDate")
    @SerializedName("expiryDate")
    var expiryDate: String? = ""
)