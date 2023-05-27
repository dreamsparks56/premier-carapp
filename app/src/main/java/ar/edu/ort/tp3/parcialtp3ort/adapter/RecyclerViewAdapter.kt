package ar.edu.ort.tp3.parcialtp3ort.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.models.CarResponse

class RecyclerViewAdapter ( private val carList: List<CarResponse>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val carModel: TextView = itemView.findViewById<TextView>(R.id.carModel)
        val fuelType: TextView = itemView.findViewById<TextView>(R.id.carFuelText)
        val drive: TextView = itemView.findViewById<TextView>(R.id.carDriveText)
        val year: TextView = itemView.findViewById<TextView>(R.id.carYearText)
        val carClass: TextView = itemView.findViewById<TextView>(R.id.carClassText)
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
        val fullCarModel = String.format("%s %s", car.make, car.model)
        holder.carModel.text = fullCarModel
        holder.drive.text = car.drive
        holder.fuelType.text = car.fuel_type
        holder.year.text = car.year
        holder.carClass.text = car.car_class
    }


}