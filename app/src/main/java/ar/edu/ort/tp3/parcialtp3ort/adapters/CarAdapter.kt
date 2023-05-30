package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import com.bumptech.glide.Glide

class CarAdapter (private val carList: List<CarResponse>): RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val carModel: TextView = itemView.findViewById<TextView>(R.id.carModel)
        val fuelType: TextView = itemView.findViewById<TextView>(R.id.carFuelText)
        val drive: TextView = itemView.findViewById<TextView>(R.id.carDriveText)
        val year: TextView = itemView.findViewById<TextView>(R.id.carYearText)
        val carClass: TextView = itemView.findViewById<TextView>(R.id.carClassText)
        val carModelImg: ImageView = itemView.findViewById<ImageView>(R.id.carModelImg)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val car= carList[position]
        val carMake = car.marca
        val fullCarModel = String.format("%s %s", carMake, car.modelo)
        holder.carModel.text = fullCarModel
        holder.drive.text = car.tipoConduccion
        holder.fuelType.text = car.combustible
        holder.year.text = car.ano
        holder.carClass.text = car.descripcionAuto
        Glide.with(holder.itemView).load(car.image).placeholder(R.drawable.avatar_car).error(Car.getImage(carMake)).fitCenter().into(holder.carModelImg)
    }


}