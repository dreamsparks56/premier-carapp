package ar.edu.ort.tp3.parcialtp3ort.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R

class MakeHolder( v: View) : RecyclerView.ViewHolder(v) {

    private var view: View

    val carMakeImg: ImageView = itemView.findViewById<ImageView>(R.id.marcaImg)

    init {
        this.view = v
    }

    fun setName(name: String) {
        val txt = this.view.findViewById<TextView>(R.id.makeName)
        txt.text = name
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