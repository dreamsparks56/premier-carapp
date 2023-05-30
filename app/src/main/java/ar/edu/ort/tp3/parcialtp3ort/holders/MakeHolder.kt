package ar.edu.ort.tp3.parcialtp3ort.holders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.Fragments.Home_fragmentDirections
import ar.edu.ort.tp3.parcialtp3ort.Models.AutoViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.card.MaterialCardView

class MakeHolder(v: View, private val store: ViewModelStoreOwner, private val navController: NavController) : RecyclerView.ViewHolder(v) {

    private var view: View
    private lateinit var viewModel: AutoViewModel
    val carMakeImg: ImageView = itemView.findViewById<ImageView>(R.id.marcaImg)

    init {
        this.view = v
        this.viewModel = ViewModelProvider(store).get(AutoViewModel::class.java)
    }

    fun setName(name: String) {
        val txt = this.view.findViewById<TextView>(R.id.makeName)
        txt.text = name
        this.view.setOnClickListener {
            val txt = this.view.findViewById<TextView>(R.id.makeName).text.toString()
            viewModel.buscar(txt, "make")
            Log.d("Search By Make ", txt)
            val action = Home_fragmentDirections.actionHomeFragmentToAutoFragment()
            navController.navigate(action)
        }
    }

    fun setCount(count: Int) {
        val txt = this.view.findViewById<TextView>(R.id.makeCount)
        txt.text = count.toString()
    }
    fun callImg(make: String): String {
        val thumbImageDirection = "https://raw.githubusercontent.com/filippofilip95/car-logos-dataset/master/logos/thumb/%s.png"
        return String.format(thumbImageDirection, make)
    }

}