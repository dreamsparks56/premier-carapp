package ar.edu.ort.tp3.parcialtp3ort.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder.APIServiceBuilder
import ar.edu.ort.tp3.parcialtp3ort.Models.AutoViewModel
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.adapters.CategoryAdapter
import ar.edu.ort.tp3.parcialtp3ort.adapters.MakeAdapter
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase.Companion.getIntance
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Car.Companion.getCarEntityFromCarResponse
import ar.edu.ort.tp3.parcialtp3ort.entities.Category
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarcasFragment : Fragment() {
    lateinit var v: View
    lateinit var marcasListView: RecyclerView
    lateinit var categoriesView: RecyclerView
    private val marcasList: MutableList<Make> = ArrayList<Make>()
    private lateinit var viewModel: AutoViewModel
    val categories: MutableList<Category> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_marcas, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(AutoViewModel::class.java)

        marcasListView = v.findViewById(R.id.marcasListView)
        categoriesView = v.findViewById(R.id.categoriesView)

        getAllCars()
        setupMakes()
        setupCategories()
    }

    private fun getAllCars() {
        val carList : MutableList<Car>? = getIntance()?.carDao()?.getAllCars()

        if(carList == null || carList.size <= 2){
            val service = APIServiceBuilder.createCarService()
            service.getCarsByFuel("gas").enqueue(object: Callback<List<CarResponse>> {
                override fun onResponse(
                    call: Call<List<CarResponse>>,
                    response: Response<List<CarResponse>>
                ) {
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
                    getIntance()?.carDao()?.insertAll(getCarEntityFromCarResponse(response.body()!!))
                    getData(getIntance()?.carDao()?.getCarsByCombustible("electricity"))
                }
                override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                }
            })

        }else{
            getData(carList)
        }
    }

    fun getData(carList: MutableList<Car>?) {
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
                setupMakes()
            }
    }
    /*private fun getLogoImages() {
        val mDbHelper = DBHelper.getIntance()//(context,null)
        val logoList : List<LogoResponse> = mDbHelper.getAllLogos()
        if(logoList.size < marcasList.size) {

            val logoService = APIServiceBuilder.createLogoService()
            for (marca in marcasList) {
                if (marca.url.isEmpty()) {
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
                if(marca.url.isEmpty()){
                    if(logoList.find{it.nombre == marca.name} != null){
                        marca.url = logoList.find{it.nombre == marca.name}!!.imagenURL
                        setupRecView()
                    }
                }
            }
        }
    }*/
    fun setupMakes() {
        Log.d("Lista de marcas", marcasList.size.toString())
        val navController = findNavController()
        marcasListView.adapter = MakeAdapter(marcasList,requireActivity(),navController)
    }

    fun setupCategories() {
            categories.add(Category(getString(R.string.category_sport),
                ContextCompat.getColor(requireContext(), R.color.yellow),
                ContextCompat.getDrawable(requireContext(), R.drawable.category_sport),
                "gas",
                "fuel_type"
            ))
            categories.add(Category(getString(R.string.category_suv),
                ContextCompat.getColor(requireContext(), R.color.blue),
                ContextCompat.getDrawable(requireContext(), R.drawable.category_suv),
                "diesel",
                "fuel_type"
            ))
            categories.add(Category(getString(R.string.category_electric),
                ContextCompat.getColor(requireContext(), R.color.teal_variant),
                ContextCompat.getDrawable(requireContext(), R.drawable.category_electric),
                "electric",
                "fuel_type"))
            Log.d("category size", categories.size.toString())
            val navController = findNavController()
            categoriesView.adapter = CategoryAdapter(categories, requireActivity(), navController)
    }


}