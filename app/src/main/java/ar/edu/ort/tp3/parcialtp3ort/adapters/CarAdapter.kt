package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.holders.CarHolder
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class CarAdapter (private val carList: MutableList<Car>, val contexto: Context, val reciclerFav:Boolean): RecyclerView.Adapter<CarHolder>() {


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
        if(!reciclerFav) {
            opcionFavoritos(holder, car)
        }else {
           esFavorito(holder, car, position)
        }

        val carMake = car.marca
        val fullCarModel = String.format("%s %s", carMake.replaceFirstChar { it.titlecase() }, car.modelo.replaceFirstChar { it.titlecase() })
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
            dialog2.setTitle("Eliminar favorito")
                .setMessage("¿Deseas eliminar este auto de tus favoritos?")
                .setPositiveButton("Eliminar") { dialog, _ ->
                    eliminar(car, position)
                }.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog2.create()
            dialog2.setCancelable(false)
            dialog2.show()

        }
    }

    private fun eliminar(car: Car, position: Int) {
        car.favorito = false
        appDatabase.getIntance()?.carDao()?.updateCar(car)
        carList.removeAt(position) //La saco de la list actual.
        notifyItemRemoved(position) //Actualiza la vista xq se borro algo.
     //notifyItemRemoved(position)//Le notifico q posición se borro para q la saque de la vista.
    }

    private fun opcionFavoritos(holder: CarHolder, car: Car) {
        holder.itemView.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(contexto)
            dialog.setTitle("Añadir a favoritos")
                .setMessage("¿Deseas añadir este auto a tus favoritos?")
                .setPositiveButton("Agregar") { dialog, _ ->
                    añadorFavorito(car)
                }

                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.create()
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    private fun añadorFavorito(car: Car) {
        println("Favorito es: " + car.favorito)
        car.favorito = true
        appDatabase.getIntance()?.carDao()?.updateCar(car)
        println("Favorito es: " + car.favorito)

       val favoritos =  appDatabase.getIntance()?.carDao()?.getFavoriteCars(true)
       // println(favoritos!![2].id)
    //    notifyDataSetChanged() //Le avisa al recycler q refresque xq hay alguna actualización de la bd.


    }


}