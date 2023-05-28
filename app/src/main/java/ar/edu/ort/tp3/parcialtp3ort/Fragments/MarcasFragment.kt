package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder.APIServiceBuilder
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.MakeAdapter
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import ar.edu.ort.tp3.parcialtp3ort.models.CarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        getCars()
        return v
    }

    private fun getCars() {
        val service = APIServiceBuilder.create()

        service.getCarList().enqueue(object: Callback<List<CarResponse>> {
            override fun onResponse(
                call: Call<List<CarResponse>>,
                response: Response<List<CarResponse>>
            ) {
                getData(response.body()!!)
            }

            override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                //Not yet implemented
            }
        })

    }

    fun getData(carList: List<CarResponse>) {
        //TODO: connect to api logic and fetch actual makes
            for(car in carList) {
                var exists: Boolean = false
                    for(marca in marcasList) {
                        val isEqual = marca.name == car.make
                        if(isEqual) {
                            marca.count++
                            exists = true
                        }
                    }
                    if(!exists) {
                        marcasList.add(Make(car.make, 1))
                    }
                }
        Log.d("Lista de marcas pre setup", marcasList.size.toString())
        setupRecView()
    }

    fun setupRecView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        marcasListView.layoutManager = linearLayoutManager
        Log.d("Lista de marcas", marcasList.size.toString())
        marcasListView.adapter = MakeAdapter(marcasList)

    }


}