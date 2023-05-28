package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder.APIServiceBuilder
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.CarAdapter
import ar.edu.ort.tp3.parcialtp3ort.models.CarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutoFragment : Fragment() {

    //private lateinit var viewModel: AutoViewModel
    private lateinit var v: View
    private lateinit var carList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v =  inflater.inflate(R.layout.fragment_auto, container, false)
        carList =  v.findViewById<RecyclerView>(R.id.carListView)
        getFragment()
        return v
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AutoViewModel::class.java)

    }*/
    private fun getFragment(){
        val service = APIServiceBuilder.create()

        service.getCarList().enqueue(object: Callback<List<CarResponse>> {
            override fun onResponse(
                call: Call<List<CarResponse>>,
                response: Response<List<CarResponse>>
            ) {
                showData(response.body()!!)


            }

            override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                //Not yet implemented
            }
        }

        )

    }

    private fun showData(carBunch: List<CarResponse>) {
        carList.apply {
           layoutManager = LinearLayoutManager(context)
            adapter = CarAdapter(carBunch)
        }
    }

}