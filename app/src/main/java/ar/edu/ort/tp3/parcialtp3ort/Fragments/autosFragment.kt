package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class autosFragment : Fragment() {
  lateinit var viewAutos:View;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewAutos = inflater.inflate(R.layout.fragment_autos, container, false)


        return view;
    }






}