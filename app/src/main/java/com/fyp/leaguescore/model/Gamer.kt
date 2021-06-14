package com.fyp.leaguescore.model

import android.os.Parcel
import android.os.Parcelable

data class Gamer(val name : String?,val riotID: String?,val user: User?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(User::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(riotID)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gamer> {
        override fun createFromParcel(parcel: Parcel): Gamer {
            return Gamer(parcel)
        }

        override fun newArray(size: Int): Array<Gamer?> {
            return arrayOfNulls(size)
        }
    }


}
