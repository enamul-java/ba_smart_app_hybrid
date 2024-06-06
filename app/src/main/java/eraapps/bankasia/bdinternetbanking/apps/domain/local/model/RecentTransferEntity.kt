package eraapps.bankasia.bdinternetbanking.apps.domain.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "recent_transfer_table", indices = arrayOf(
        Index(
            value = ["id", "amount"],
            unique = true
        )
    )
)
data class RecentTransferEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "acNo")
    @SerializedName("acNo")
    var acNo: String = "",

    @ColumnInfo(name = "acTitle")
    @SerializedName("acTitle")
    var acTitle: String = "",

    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    var amount: String = "",

    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date: String = "",

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String = "",

    @ColumnInfo(name = "sourceAc")
    @SerializedName("sourceAc")
    var sourceAc: String = ""
)