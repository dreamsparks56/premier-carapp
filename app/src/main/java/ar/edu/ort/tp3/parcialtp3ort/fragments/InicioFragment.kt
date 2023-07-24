package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.edu.ort.tp3.parcialtp3ort.R


class InicioFragment : Fragment() {
    lateinit var viewHome: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewHome = inflater.inflate(R.layout.fragment_home_fragment, container, false)

        return viewHome
    }





}