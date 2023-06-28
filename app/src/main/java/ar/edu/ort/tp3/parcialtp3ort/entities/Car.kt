package ar.edu.ort.tp3.parcialtp3ort.entities

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.Models.LogoResponse

@Entity(tableName = "cars")
class Car(id : Int, modelo: String, marca : String, descripcion : String, ano: String, combustible : String, caja : String, tipoConduccion: String, motor : Int, cilindrada : Double, consumoCiudad : Double, consumoAutopista : Double, consumoCombinado : Int, image : String?, favorito : Boolean) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int

    @ColumnInfo(name = "modelo")
    var modelo: String

    @ColumnInfo(name = "marca")
    var marca: String

    @ColumnInfo(name = "descripcion")
    var descripcion: String

    @ColumnInfo(name = "ano")
    var ano: String

    @ColumnInfo(name = "combustible")
    var combustible: String

    @ColumnInfo(name = "caja")
    var caja: String

    @ColumnInfo(name = "tipoConduccion")
    var tipoConduccion: String

    @ColumnInfo(name = "motor")
    var motor: Int

    @ColumnInfo(name = "cilindrada")
    var cilindrada: Double

    @ColumnInfo(name = "consumoCiudad")
    var consumoCiudad: Double

    @ColumnInfo(name = "consumoAutopista")
    var consumoAutopista: Double

    @ColumnInfo(name = "consumoCombinado")
    var consumoCombinado: Int

    @ColumnInfo(name = "image")
    var image: String

    @ColumnInfo(name = "favorito")
    var favorito : Boolean
    init {
        this.id = id
        this.modelo = if (modelo.isNullOrEmpty()) "" else modelo
        this.marca = if (marca.isNullOrEmpty()) "" else marca
        this.descripcion = if (descripcion.isNullOrEmpty()) "" else descripcion
        this.ano = if (ano.isNullOrEmpty()) "" else ano
        this.combustible =  if (combustible.isNullOrEmpty()) "" else combustible
        this.caja =  if (caja.isNullOrEmpty()) "" else caja
        this.tipoConduccion = if (tipoConduccion.isNullOrEmpty()) "" else tipoConduccion
        this.motor = if (motor.toString().isNullOrEmpty()) 0 else motor
        this.cilindrada = if (cilindrada.toString().isNullOrEmpty()) 0.0 else cilindrada
        this.consumoCiudad = if (consumoCiudad.toString().isNullOrEmpty()) 0.0 else consumoCiudad
        this.consumoAutopista = if (consumoAutopista.toString().isNullOrEmpty()) 0.0 else consumoAutopista
        this.consumoCombinado = if (consumoCombinado.toString().isNullOrEmpty()) 0 else consumoCombinado
        //this.image = if (image.toString().isNullOrEmpty()) "" else image.toString()
        this.image = if (!image.isNullOrEmpty()) image.toString() else Make.Constants.getImage(marca)
        this.favorito = favorito
    }

    companion object {

        fun getCarEntityFromCarResponse(carRes:List<CarResponse>): MutableList<Car>?{
            var carRet:MutableList<Car> = mutableListOf()
            carRes.forEach { car ->
                //var ret : Car = Car(0, if (car.modelo.isNullOrEmpty()) "" else car.modelo, if (car.marca.isNullOrEmpty()) "" else car.marca, car.descripcionAuto, car.ano, car.combustible, car.caja, getFullDriveName(car.tipoConduccion), car.motor, car.cilindrada, car.consumoCiudad, car.consumoAutopista, car.consumoCombinado, car.image, false)
                var ret : Car = Car(0, if (car.modelo.isNullOrEmpty()) "" else car.modelo, if (car.marca.isNullOrEmpty()) "" else car.marca, if (car.descripcionAuto.isNullOrEmpty()) "" else car.descripcionAuto, if (car.ano.isNullOrEmpty()) "" else car.ano,                    if (car.combustible.isNullOrEmpty()) "" else car.combustible, if (car.caja.isNullOrEmpty()) "" else car.caja, if (car.tipoConduccion.isNullOrEmpty()) "" else getFullDriveName(car.tipoConduccion), if (car.motor.toString().isNullOrEmpty()) 0 else car.motor, if (car.cilindrada.toString().isNullOrEmpty()) 0.0 else car.cilindrada, if (car.consumoCiudad.toString().isNullOrEmpty()) 0.0 else car.consumoCiudad, if (car.consumoAutopista.toString().isNullOrEmpty()) 0.0 else car.consumoAutopista, if (car.consumoCombinado.toString().isNullOrEmpty()) 0 else car.consumoCombinado, if (!car.image.isNullOrEmpty()) car.image.toString() else Make.Constants.getImage(car.marca), false)
                carRet.add(ret)
            }
            return carRet
        }
        fun getFullDriveName(drive: String?): String {
            val fullDrive: String
            when(drive) {
                "2wd" -> fullDrive = "Front Wheel"
                "rwd" -> fullDrive = "Rear Wheel"
                "awd" -> fullDrive = "All Wheel"
                "4wd" -> fullDrive = "Four Wheel"
                else -> {
                    fullDrive = "Unspecified"
                }
            }
            return fullDrive
        }
    }
}