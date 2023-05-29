package ar.edu.ort.tp3.parcialtp3ort.entities

import android.os.Parcel
import android.os.Parcelable

class Make(name: String?, count: Int?) : Parcelable {
    var name: String = ""
    var count: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    class Constants {
        companion object {
            val THUMB_IMAGE_DIRECTION: String = "https://raw.githubusercontent.com/filippofilip95/car-logos-dataset/master/logos/thumb/%s.png"
            fun getImage(carMake: String): String = String.format(THUMB_IMAGE_DIRECTION, carMake)
        }

    }

    init {
        this.name = name!!
        this.count = count!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Make> {
        override fun createFromParcel(parcel: Parcel): Make {
            return Make(parcel)
        }

        override fun newArray(size: Int): Array<Make?> {
            return arrayOfNulls(size)
        }
    }


}