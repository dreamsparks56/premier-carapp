package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.MakeAdapter
import ar.edu.ort.tp3.parcialtp3ort.entities.Make

class MarcasFragment : Fragment() {
    lateinit var v: View
    lateinit var marcasListView: RecyclerView
    var marcasList: MutableList<Make> = ArrayList<Make>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_marcas, container, false)
        marcasListView = v.findViewById<RecyclerView>(R.id.marcasListView)
        getData()
        setupRecView()
        return v
    }

    fun getData() {
        //TODO: connect to api logic and fetch actual makes
            marcasList.add(Make("Toyota", 6))
            marcasList.add(Make("Nissan", 3))
            marcasList.add(Make("Renault", 16))
            marcasList.add(Make("Maserati", 6))
            marcasList.add(Make("Messirati", 3))
            marcasList.add(Make("Reno", 45))
            marcasList.add(Make("Lamborgotti", 1))
    }

    fun setupRecView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        marcasListView.layoutManager = linearLayoutManager
        Log.d("Lista de marcas", marcasList.toString())
        marcasListView.adapter = MakeAdapter(marcasList)

    }


}