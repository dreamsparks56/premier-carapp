package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.tools.ImageFetching.Companion.getImageWebOrLocal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class PerfilFragment() : Fragment() {
    lateinit var vista: View
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: LoginViewModel
    lateinit var nombre: TextView
    lateinit var email: TextView
    private lateinit var photoUrl: ImageView

    private val selectPicture = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        if (it != null) {
            Log.d("PhotoPicker", "Selected URI: $it")
            auth = Firebase.auth
            viewModel.updatePhotoUri(auth.currentUser, it)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_perfil, container, false)

        val bottomSheet = ProfilePictureModal()


        nombre = vista.findViewById(R.id.nameUser_perfil)
        email = vista.findViewById(R.id.emailUser_perfil)
        photoUrl = vista.findViewById(R.id.img_perfil)
        photoUrl.setOnClickListener {

            bottomSheet.show(this.parentFragmentManager, ProfilePictureModal.TAG)
        }
        val bottomSheetGalleryButton: Button = vista.findViewById(R.id.gallerySelect)
        bottomSheetGalleryButton.setOnClickListener {
            selectImage()
        }


        // Obtener referencia al ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        // Acceder a las propiedades o métodos del ViewModel según sea necesario
        nombre.text = viewModel.usuario.value.toString()
        email.text = viewModel.email.value.toString()
        Log.d("imagen", viewModel.photoUrl.toString())
        getImage()


        return vista
    }

    private fun getImage() {
        getImageWebOrLocal(vista, photoUrl, viewModel.photoUrl, R.drawable.fotoperfil)
    }

    fun selectImage() {
        selectPicture.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


}