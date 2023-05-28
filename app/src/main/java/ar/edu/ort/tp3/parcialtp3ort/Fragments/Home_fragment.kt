package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.edu.ort.tp3.parcialtp3ort.R


class Home_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var viewHome: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewHome = inflater.inflate(R.layout.fragment_home_fragment, container, false)
        return viewHome
    }





}