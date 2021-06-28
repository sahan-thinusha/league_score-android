package com.fyp.leaguescore.model

import android.os.Parcel
import android.os.Parcelable

data class Champion(var name: String?, var index: String?, var avatar: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(index)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Champion> {
        override fun createFromParcel(parcel: Parcel): Champion {
            return Champion(parcel)
        }

        override fun newArray(size: Int): Array<Champion?> {
            return arrayOfNulls(size)
        }
    }
}
