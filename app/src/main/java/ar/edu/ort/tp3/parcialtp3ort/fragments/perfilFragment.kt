package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R




class perfilFragment : Fragment() {
    lateinit var vista:View;
    lateinit var viewModel:LoginViewModel
    lateinit var nombre:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista=  inflater.inflate(R.layout.fragment_perfil, container, false)

        nombre = vista.findViewById(R.id.nameUser_perfil)

        // Obtener referencia al ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        // Acceder a las propiedades o métodos del ViewModel según sea necesario
        nombre.text = viewModel.usuario.value.toString()



        return vista
    }


}