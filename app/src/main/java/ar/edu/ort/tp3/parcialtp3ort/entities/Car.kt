package ar.edu.ort.tp3.parcialtp3ort.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse

@Entity(tableName = "cars")
class Car(
    id: Int,
    modelo: String,
    marca: String,
    descripcion: String,
    ano: String,
    combustible: String,
    caja: String,
    tipoConduccion: String,
    motor: Int,
    cilindrada: Double,
    consumoCiudad: Double,
    consumoAutopista: Double,
    consumoCombinado: Int,
    image: String?,
    favorito: Boolean
) {

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
    var favorito: Boolean

    init {
        this.id = id
        this.modelo = if (modelo.isEmpty()) "" else modelo
        this.marca = if (marca.isEmpty()) "" else marca
        this.descripcion = if (descripcion.isEmpty()) "" else descripcion
        this.ano = if (ano.isEmpty()) "" else ano
        this.combustible = if (combustible.isEmpty()) "" else combustible
        this.caja = if (caja.isEmpty()) "" else caja
        this.tipoConduccion = if (tipoConduccion.isEmpty()) "" else tipoConduccion
        this.motor = if (motor.toString().isEmpty()) 0 else motor
        this.cilindrada = if (cilindrada.toString().isEmpty()) 0.0 else cilindrada
        this.consumoCiudad = if (consumoCiudad.toString().isEmpty()) 0.0 else consumoCiudad
        this.consumoAutopista =
            if (consumoAutopista.toString().isEmpty()) 0.0 else consumoAutopista
        this.consumoCombinado =
            if (consumoCombinado.toString().isEmpty()) 0 else consumoCombinado
        //this.image = if (image.toString().isEmpty()) "" else image.toString()
        this.image =
            if (!image.isNullOrEmpty()) image.toString() else Make.Constants.getImage(marca)
        this.favorito = favorito
    }

    companion object {

        fun getCarEntityFromCarResponse(carRes: List<CarResponse>): MutableList<Car> {
            val carRet: MutableList<Car> = mutableListOf()
            carRes.forEach { car ->
                //var ret : Car = Car(0, if (car.modelo.isEmpty()) "" else car.modelo, if (car.marca.isEmpty()) "" else car.marca, car.descripcionAuto, car.ano, car.combustible, car.caja, getFullDriveName(car.tipoConduccion), car.motor, car.cilindrada, car.consumoCiudad, car.consumoAutopista, car.consumoCombinado, car.image, false)
                val ret = Car(
                    0,
                    if (car.modelo.isEmpty()) "" else car.modelo,
                    if (car.marca.isEmpty()) "" else car.marca,
                    if (car.descripcionAuto.isEmpty()) "" else car.descripcionAuto,
                    if (car.ano.isEmpty()) "" else car.ano,
                    if (car.combustible.isEmpty()) "" else car.combustible,
                    if (car.caja.isEmpty()) "" else car.caja,
                    if (car.tipoConduccion.isEmpty()) "" else getFullDriveName(car.tipoConduccion),
                    if (car.motor.toString().isEmpty()) 0 else car.motor,
                    if (car.cilindrada.toString().isEmpty()) 0.0 else car.cilindrada,
                    if (car.consumoCiudad.toString().isEmpty()) 0.0 else car.consumoCiudad,
                    if (car.consumoAutopista.toString()
                            .isEmpty()
                    ) 0.0 else car.consumoAutopista,
                    if (car.consumoCombinado.toString()
                            .isEmpty()
                    ) 0 else car.consumoCombinado,
                    if (!car.image.isNullOrEmpty()) car.image.toString() else Make.Constants.getImage(
                        car.marca
                    ),
                    false
                )
                carRet.add(ret)
            }
            return carRet
        }

        fun getFullDriveName(drive: String?): String {
            val fullDrive: String
            when (drive) {
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