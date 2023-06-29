package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.CarAdapter
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase
import ar.edu.ort.tp3.parcialtp3ort.entities.Car


class FavoritosFragment : Fragment() {
    lateinit var v:View
    lateinit var vistaReciclable:RecyclerView
    lateinit var autos:MutableList<Car>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_favoritos, container, false)
        vistaReciclable = v.findViewById(R.id.reciclyerFavs)
        showData()
        return v
    }

    private fun showData() {
       autos = appDatabase.getIntance()?.carDao()?.getFavoriteCars(favorite = true)!!
        vistaReciclable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CarAdapter(autos, context, true)
        }
    }


}