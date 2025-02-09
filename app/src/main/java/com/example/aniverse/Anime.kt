package com.example.aniverse.model

import android.os.Parcel
import android.os.Parcelable

data class Anime(
    val nombre: String,
    val genero: String,
    val estudios: String,
    val imagen: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(genero)
        parcel.writeString(estudios)
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Anime> {
        override fun createFromParcel(parcel: Parcel): Anime {
            return Anime(parcel)
        }

        override fun newArray(size: Int): Array<Anime?> {
            return arrayOfNulls(size)
        }
    }
}
