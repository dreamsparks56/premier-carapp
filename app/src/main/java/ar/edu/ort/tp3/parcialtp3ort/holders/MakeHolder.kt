package ar.edu.ort.tp3.parcialtp3ort.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R

class MakeHolder( v: View) : RecyclerView.ViewHolder(v) {

    private var view: View

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

}