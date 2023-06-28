package ar.edu.ort.tp3.parcialtp3ort.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder.APIServiceBuilder
import ar.edu.ort.tp3.parcialtp3ort.Models.AutoViewModel
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.Models.LogoResponse
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.MakeAdapter
import ar.edu.ort.tp3.parcialtp3ort.database.CarDao
import ar.edu.ort.tp3.parcialtp3ort.database.DBHelper
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase.Companion.getIntance
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Car.Companion.getCarEntityFromCarResponse
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarcasFragment : Fragment() {
    lateinit var v: View
    lateinit var marcasListView: RecyclerView
    var marcasList: MutableList<Make> = ArrayList<Make>()
    lateinit var btnSportF: ImageButton
    lateinit var btnSuvF: ImageButton
    lateinit var btnElectricF: ImageButton
    private lateinit var viewModel: AutoViewModel
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
        btnSportF = v.findViewById<ImageButton>(R.id.sportFilter)
        btnSuvF = v.findViewById<ImageButton>(R.id.suvFilter)
        btnElectricF = v.findViewById<ImageButton>(R.id.electricFilter)
        getAllCars()
        setupRecView()
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(AutoViewModel::class.java)

        btnSportF.setOnClickListener{
            viewModel.buscar("gas", "fuel_type")
            val action = Home_fragmentDirections.actionHomeFragmentToAutoFragment()
            Log.d("btnSportF", "gas")
            findNavController().navigate(action)
        }
        btnSuvF.setOnClickListener{
            viewModel.buscar("diesel", "fuel_type")
            val action = Home_fragmentDirections.actionHomeFragmentToAutoFragment()
            Log.d("btnSuvF", "diesel")
            findNavController().navigate(action)
        }
        btnElectricF.setOnClickListener{
            viewModel.buscar("electric", "fuel_type")
            Log.d("btnElectricF", "electric")
            val action = Home_fragmentDirections.actionHomeFragmentToAutoFragment()
            findNavController().navigate(action)
        }
    }
    private fun getAllCars() {
        //val mDbHelper = DBHelper.getIntance()//(context,null)
        //val carList : List<CarResponse> = mDbHelper.getAllCars()
        //var db: appDatabase? = getIntance()//appDatabase.getAppDataBase(v.context)//getIntance()//appDatabase.getAppDataBase(v.context)
        var carList : MutableList<Car>? = getIntance()?.carDao()?.getAllCars()

        if(carList== null || (carList != null && carList.size <= 2)){
            val service = APIServiceBuilder.createCarService()
            service.getCarsByFuel("gas").enqueue(object: Callback<List<CarResponse>> {
                override fun onResponse(
                    call: Call<List<CarResponse>>,
                    response: Response<List<CarResponse>>
                ) {
                    //mDbHelper.addCars(response.body()!!)
                    //getData(response.body()!!)
                    getIntance()?.carDao()?.insertAll(getCarEntityFromCarResponse(response.body()!!))
                    getData(getIntance()?.carDao()?.getCarsByCombustible("gas"))
                }
                override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                    //Not yet implemented
                }
            })
            service.getCarsByFuel("diesel").enqueue(object: Callback<List<CarResponse>> {
                override fun onResponse(
                    call: Call<List<CarResponse>>,
                    response: Response<List<CarResponse>>
                ) {
                    //mDbHelper.addCars(response.body()!!)
                    //getData(response.body()!!)
                    getIntance()?.carDao()?.insertAll(getCarEntityFromCarResponse(response.body()!!))
                    getData(getIntance()?.carDao()?.getCarsByCombustible("diesel"))
                }
                override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                    //Not yet implemented
                }
            })
            service.getCarsByFuel("electricity").enqueue(object: Callback<List<CarResponse>> {
                override fun onResponse(
                    call: Call<List<CarResponse>>,
                    response: Response<List<CarResponse>>
                ) {
                    //mDbHelper.addCars(response.body()!!)
                    //getData(response.body()!!)
                    getIntance()?.carDao()?.insertAll(getCarEntityFromCarResponse(response.body()!!))
                    getData(getIntance()?.carDao()?.getCarsByCombustible("electricity"))
                }
                override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                    //Not yet implemented
                }
            })

        }else{
            getData(carList)
        }
    }

    fun getData(carList: MutableList<Car>?) {//(carList: List<CarResponse>) {
            if(carList != null)
            {
                for(car in carList)
                {
                    var exists = false
                    for(marca in marcasList) {
                        val isEqual = marca.name == car.marca
                        if(isEqual) {
                            marca.count++
                            exists = true
                        }
                    }
                    if(!exists) {
                        marcasList.add(Make(car.marca, car.image,1))
                    }
                }
                Log.d("Lista de marcas pre setup", marcasList.size.toString())
                //getLogoImages()
                setupRecView()
            }
    }
    private fun getLogoImages() {
        val mDbHelper = DBHelper.getIntance()//(context,null)
        val logoList : List<LogoResponse> = mDbHelper.getAllLogos()
        if(logoList.size < marcasList.size) {

            val logoService = APIServiceBuilder.createLogoService()
            for (marca in marcasList) {
                if (marca.url.isNullOrEmpty()) {
                    logoService.getLogoByName(marca.name)
                        .enqueue(object : Callback<List<LogoResponse>> {
                            override fun onResponse(
                                call: Call<List<LogoResponse>>,
                                response: Response<List<LogoResponse>>
                            ) {
                                if (response.isSuccessful) {
                                    val logos = response.body()
                                    if (!logos.isNullOrEmpty()) {
                                        marca.url = logos[0].imagenURL
                                        mDbHelper.addLogos(logos)
                                        setupRecView()
                                    }
                                } else {
                                    // Handle error
                                }
                            }

                            override fun onFailure(call: Call<List<LogoResponse>>, t: Throwable) {
                                // Handle failure
                            }
                        })
                }
            }
        } else{
            for(marca in marcasList){
                if(marca.url.isNullOrEmpty()){
                    if(logoList.find{it.nombre == marca.name} != null){
                        marca.url = logoList.find{it.nombre == marca.name}!!.imagenURL
                        setupRecView()
                    }
                }
            }
        }
    }
    fun setupRecView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        marcasListView.layoutManager = linearLayoutManager
        Log.d("Lista de marcas", marcasList.size.toString())
        val navController = findNavController()

        marcasListView.adapter = MakeAdapter(marcasList,requireActivity(),navController)

    }


}