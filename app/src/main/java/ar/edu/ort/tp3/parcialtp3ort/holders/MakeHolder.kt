package ar.edu.ort.tp3.parcialtp3ort.holders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.fragments.Home_fragmentDirections
import ar.edu.ort.tp3.parcialtp3ort.Models.AutoViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.tools.ImageFetching

class MakeHolder(v: View, private val store: ViewModelStoreOwner, private val navController: NavController) : RecyclerView.ViewHolder(v) {

    private var view: View
    private var viewModel: AutoViewModel
    private var carMakeImg: ImageView = itemView.findViewById<ImageView>(R.id.marcaImg)

    init {
        this.view = v
        this.viewModel = ViewModelProvider(store).get(AutoViewModel::class.java)
    }

    fun setName(name: String) {
        val txt = this.view.findViewById<TextView>(R.id.makeName)
        txt.text = name.replaceFirstChar { it.titlecase() }
        this.view.setOnClickListener {
            val inputTxt = txt.text.toString()
            viewModel.buscar(inputTxt, "make")
            Log.d("Search By Make ", inputTxt)
            val action = Home_fragmentDirections.actionHomeFragmentToAutoFragment()
            navController.navigate(action)
        }
    }

    fun setCount(count: Int) {
        val txt = this.view.findViewById<TextView>(R.id.makeCount)
        txt.text = String.format("+%s", count.toString())
    }
    fun callImg(makeN: String?, make: String?) {
        ImageFetching.getImageManaged(this.itemView, this.carMakeImg, makeN, make, R.drawable.avatar_car)
    }

}