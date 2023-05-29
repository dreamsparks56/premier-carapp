package ar.edu.ort.tp3.parcialtp3ort.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import com.bumptech.glide.Glide

class CarHolder( v: View) : RecyclerView.ViewHolder(v) {
    val carModelImg: ImageView = itemView.findViewById<ImageView>(R.id.carModelImg)

    private var view: View
    init {
        this.view = v
    }

    fun setModelName(name: String) {
        val carModel = this.view.findViewById<TextView>(R.id.carModel)
        carModel.text = name
    }

    fun setModelDrive(drive: String?) {
        val driveText = this.view.findViewById<TextView>(R.id.carDriveText)
        driveText.text = Car.getFullDriveName(drive)
    }

    fun setFuelType(fuelType: String) {
        val fuelTypeText = this.view.findViewById<TextView>(R.id.carFuelText)
        fuelTypeText.text = fuelType.replaceFirstChar { it.titlecase() }
    }

    fun setYear(year: String) {
        val yearText = this.view.findViewById<TextView>(R.id.carYearText)
        yearText.text = year
    }

    fun setClass(fuelType: String) {
        val fuelTypeText = this.view.findViewById<TextView>(R.id.carFuelText)
        fuelTypeText.text = fuelType.replaceFirstChar { it.titlecase() }
    }

    fun callImg(make: String) {
        Glide
            .with(this.itemView)
            .load(Make.Constants.getImage(make)).fitCenter()
            .error(
                Glide
                    .with(this.itemView)
                    .load(Make.Constants.getImage("international"))
            )
            .into(this.carModelImg)
    }




}