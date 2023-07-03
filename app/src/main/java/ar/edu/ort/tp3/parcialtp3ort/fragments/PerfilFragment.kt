package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.tools.ImageFetching.Companion.getImageWebOrLocal


class PerfilFragment : Fragment() {
    lateinit var vista:View
    lateinit var viewModel:LoginViewModel
    lateinit var nombre:TextView
    lateinit var email:TextView
    lateinit var photoUrl:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vista=  inflater.inflate(R.layout.fragment_perfil, container, false)

        nombre = vista.findViewById(R.id.nameUser_perfil)
        email = vista.findViewById(R.id.emailUser_perfil)
        photoUrl = vista.findViewById(R.id.img_perfil)
        photoUrl.setOnClickListener{
            val bottomSheet = ProfilePictureModal()

            bottomSheet.show(this.parentFragmentManager, ProfilePictureModal.TAG)
        }


        // Obtener referencia al ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        // Acceder a las propiedades o métodos del ViewModel según sea necesario
        nombre.text = viewModel.usuario.value.toString()
        email.text = viewModel.email.value.toString()
        getImageWebOrLocal(vista, photoUrl, viewModel.photoUrl, R.drawable.fotoperfil)



        return vista
    }





}