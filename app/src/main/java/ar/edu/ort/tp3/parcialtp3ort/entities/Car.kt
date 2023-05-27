/*package ar.edu.ort.tp3.parcialtp3ort.entities

import android.os.Parcel
import android.os.Parcelable

class Car(car_class: String?, fuel_type: String?, drive: String?, make: String?, model: String?, year: String?) : Parcelable {
    val car_class: String = ""
    val fuel_type: String = ""
    val drive: String = ""
    val make: String = "";
    val model: String = "";
    val year: String = "";

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    init {
        this.car_class = car_class!!
        this.fuel_type = fuel_type!!
        this.drive = drive!!
        this.model = model!!
        this.make = make!!
        this.year = year!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(car_class)
        parcel.writeString(fuel_type)
        parcel.writeString(drive)
        parcel.writeString(model)
        parcel.writeString(make)
        parcel.writeString(year)
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }


    }
}*/