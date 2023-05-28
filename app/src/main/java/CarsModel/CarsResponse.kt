package CarsModel
import com.google.gson.annotations.SerializedName

fun getMarcasUnicas(cars: List<Car>): Array<String> {
    val marcas = cars.map{ car.marca }.distinct()
    return marcas.toTypedArray()
}

data class CarsResponse{
    val results : List<Car>,
}

data class Car{
    @SerializedName("city_mpg")
    val consumoCiudad: Double,
    @SerializedName("class")
    val descripcionAuto: String,
    @SerializedName("combination_mpg")
    val consumoCombinado: Int,
    @SerializedName("cylinders")
    val motor: Int,
    @SerializedName("displacement")
    val cilindrada: Double,
    @SerializedName("drive")
    val tipoConduccion: String,
    @SerializedName("fuel_type")
    val combustible: String,
    @SerializedName("highway_mpg")
    val consumoAutopista: Double,
    @SerializedName("make")
    val marca: String,
    @SerializedName("model")
    val modelo: String,
    @SerializedName("transmission")
    val caja: String,
    @SerializedName("year")
    val ano: String,
    val image: String? = null,
}