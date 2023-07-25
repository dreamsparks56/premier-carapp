package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.holders.CarHolder
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Favorito
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class CarAdapter(
    private val carList: MutableList<Car>,
    private val contexto: Context,
    private val reciclerFav: Boolean,
    val email: String
) : RecyclerView.Adapter<CarHolder>() {


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

        val car = carList[position]
        if (!reciclerFav) {
            opcionFavoritos(holder, car)
        } else {
            esFavorito(holder, car, position)
        }

        val carMake = car.marca
        val fullCarModel = String.format(
            "%s %s",
            carMake.replaceFirstChar { it.titlecase() },
            car.modelo.replaceFirstChar { it.titlecase() })
        holder.setModelName(fullCarModel)
        holder.setModelDrive(car.tipoConduccion)
        holder.setFuelType(car.combustible)
        holder.setYear(car.ano)
        holder.setClass(car.descripcion)
        holder.callImg(car.image, car.marca)
    }

    private fun esFavorito(holder: CarHolder, car: Car, position: Int) {
        holder.itemView.setOnClickListener {
            val dialog2 = MaterialAlertDialogBuilder(contexto)
            dialog2.setTitle(Resources.getSystem().getString(R.string.favorites_delete))
                .setMessage(Resources.getSystem().getString(R.string.favorites_delete_prompt))
                .setPositiveButton(Resources.getSystem().getString(R.string.actions_remove)) { dialog, _ ->
                    eliminar(car, position)
                }.setNegativeButton(Resources.getSystem().getString(R.string.actions_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
            dialog2.create()
            dialog2.setCancelable(false)
            dialog2.show()

        }
    }


    private fun eliminar(car: Car, position: Int) {
        appDatabase.getInstance()?.carDao()?.delete(car)
        carList.removeAt(position) //La saco de la list actual.
        notifyItemRemoved(position)
        //Actualiza la vista xq se borro algo.
        //notifyItemRemoved(position)//Le notifico q posición se borro para q la saque de la vista.
    }


    private fun opcionFavoritos(holder: CarHolder, car: Car) {
        holder.itemView.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(contexto)
            dialog.setTitle(Resources.getSystem().getString(R.string.favorites_add))
                .setMessage(Resources.getSystem().getString(R.string.favorites_add_prompt))
                .setPositiveButton(Resources.getSystem().getString(R.string.actions_add)) { _, _ ->
                    addFavorito(car)
                }

                .setNegativeButton(Resources.getSystem().getString(R.string.actions_cancel)) { self, _ ->
                    self.dismiss()
                }
            dialog.create()
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    private fun addFavorito(car: Car) {

        try {
            val fav = Favorito(email, idAuto = car.id)
            appDatabase.getInstance()?.favDao()?.insertFav(fav)
            println("------No arroja excepción-------------")
        } catch (e: Exception) {
            Toast.makeText(
                contexto,
                Resources.getSystem().getString(R.string.favorites_already_added),
                Toast.LENGTH_SHORT
            ).show()
            println("------Arroja excepción-------------")

        }

    }


}