package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProfilePictureModal : BottomSheetDialogFragment() {
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_profile_picture_modal, container, false)

        val changePicture: CardView = v.findViewById(R.id.newImageButton)

        changePicture.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCameraFragment()
            this.requireParentFragment().requireParentFragment().findNavController().navigate(action)
            dismiss()
        }



        return v
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }


}