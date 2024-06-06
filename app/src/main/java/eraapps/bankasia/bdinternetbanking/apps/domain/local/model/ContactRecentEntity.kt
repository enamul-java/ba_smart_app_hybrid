package eraapps.bankasia.bdinternetbanking.apps.domain.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "contact_table", indices = arrayOf(
        Index(
            value = ["id", "phone"],
            unique = true
        )
    )
)
data class ContactRecentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    var phone: String = "",

    @ColumnInfo(name = "photoUri")
    @SerializedName("photoUri")
    var photoUri: String = ""
)