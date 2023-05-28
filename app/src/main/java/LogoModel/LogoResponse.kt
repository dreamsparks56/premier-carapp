package LogoModel
import com.google.gson.annotations.SerializedName


data class LogoResponse{
    val results : List<Logo>,
}

data class Logo{
    @SerializedName("name")
    val nombre : String,
    @SerializedName("ticker")
    val abreviatura: String,
    @SerializedName("image")
    val imagenURL: String,
}