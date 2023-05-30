import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AutoViewModel: ViewModel() {

    val tipoBusqueda = MutableLiveData<String>()
    val campo = MutableLiveData<String>()


    fun buscar(type: String, field: String) {
        tipoBusqueda.value = type
        campo.value = field
    }
}