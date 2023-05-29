package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.holders.CarHolder
import ar.edu.ort.tp3.parcialtp3ort.models.CarResponse

class CarAdapter (private val carList: List<CarResponse>): RecyclerView.Adapter<CarHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_card, parent, false)
        return CarHolder(view)
    }

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(
        holder: CarHolder,
        position: Int,
    ) {
        val car= carList[position]
        val carMake = car.make
        val fullCarModel = String.format("%s %s", carMake.replaceFirstChar { it.titlecase() }, car.model.replaceFirstChar { it.titlecase() })
        holder.setModelName(fullCarModel)
        holder.setModelDrive(car.drive)
        holder.setFuelType(car.fuel_type)
        holder.setYear(car.year)
        holder.setClass(car.car_class)
        holder.callImg(carMake)
    }


}