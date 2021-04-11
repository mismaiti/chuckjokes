package com.mismaiti.chuckjokes.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class JokesResponse(
    val result: List<JokesEntity>?
)

@Entity(primaryKeys = ["id"])
data class JokesEntity(
    @SerializedName("data_id")
    var id: Int,
    @SerializedName("id")
    val jokesId: String?,
    var category: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("icon_url")
    val iconUrl: String?,
    val value: String?
) : Parcelable {

    constructor(jokesParcel: Parcel): this(
        jokesParcel.readInt(),
        jokesParcel.readString(),
        jokesParcel.readString(),
        jokesParcel.readString(),
        jokesParcel.readString(),
        jokesParcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(jokesId)
        dest?.writeString(category)
        dest?.writeString(updatedAt)
        dest?.writeString(iconUrl)
        dest?.writeString(value)
    }

    companion object {
        @JvmField
        val CREATOR : Parcelable.Creator<JokesEntity> = object : Parcelable.Creator<JokesEntity> {
            override fun createFromParcel(source: Parcel): JokesEntity = JokesEntity(source)
            override fun newArray(size: Int): Array<JokesEntity?> = arrayOfNulls(size)
        }

    }
}
