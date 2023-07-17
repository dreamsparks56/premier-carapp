package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class ProfilePictureModal : BottomSheetDialogFragment() {
    lateinit var v: View
    private lateinit var auth: FirebaseAuth
    private lateinit var model: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_profile_picture_modal, container, false)

        val changePicture: CardView = v.findViewById(R.id.newImageButton)
        val selectPicture: CardView = v.findViewById(R.id.galleryButton)

        changePicture.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCameraFragment()
            this.requireParentFragment().requireParentFragment().findNavController().navigate(action)
            dismiss()
        }
        selectPicture.setOnClickListener {
            lifecycleScope.launch {
                imageChooser()
                dismiss()
            }
        }


        return v
    }

    private fun imageChooser() {
        getImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
    private val getImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            auth = Firebase.auth
            model.updatePhotoUri(auth.currentUser, uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }


}