package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.holders.CarHolder
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse

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
        val carMake = car.marca
        val fullCarModel = String.format("%s %s", carMake.replaceFirstChar { it.titlecase() }, car.modelo.replaceFirstChar { it.titlecase() })
        holder.setModelName(fullCarModel)
        holder.setModelDrive(car.tipoConduccion)
        holder.setFuelType(car.combustible)
        holder.setYear(car.ano)
        holder.setClass(car.descripcionAuto)
        holder.callImg(car.image, car.marca)
    }


}