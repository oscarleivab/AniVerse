package com.example.aniverse.model

import android.os.Parcel
import android.os.Parcelable

data class AnimeDetail(
    val nombre: String,
    val genero: String,
    val estudios: String,
    val imagen: String,
    val descripcion: String,
    val episodios: Int,
    val fechaLanzamiento: String,
    val puntuacion: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(genero)
        parcel.writeString(estudios)
        parcel.writeString(imagen)
        parcel.writeString(descripcion)
        parcel.writeInt(episodios)
        parcel.writeString(fechaLanzamiento)
        parcel.writeDouble(puntuacion)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<AnimeDetail> {
        override fun createFromParcel(parcel: Parcel): AnimeDetail {
            return AnimeDetail(parcel)
        }

        override fun newArray(size: Int): Array<AnimeDetail?> {
            return arrayOfNulls(size)
        }
    }
}
