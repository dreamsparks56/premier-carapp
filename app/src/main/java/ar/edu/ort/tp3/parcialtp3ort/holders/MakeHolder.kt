package ar.edu.ort.tp3.parcialtp3ort.holders

import AutoViewModel
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.card.MaterialCardView

class MakeHolder( v: View) : RecyclerView.ViewHolder(v) {

    private var view: View

    val carMakeImg: ImageView = itemView.findViewById<ImageView>(R.id.marcaImg)

    init {
        this.view = v
    }

    fun setName(name: String) {
        val txt = this.view.findViewById<TextView>(R.id.makeName)
        txt.text = name
        this.view.findViewById<MaterialCardView>(R.id.cardView).setOnClickListener {
            var viewModel: AutoViewModel = AutoViewModel()
            viewModel.buscar(name, "make")
            Log.d("Search By Make ", name)
            // TODO: Navigate to AutoFragment - viewModel
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