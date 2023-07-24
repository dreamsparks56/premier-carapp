package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import ar.edu.ort.tp3.parcialtp3ort.holders.MakeHolder


class MakeAdapter(
    private val makeList: MutableList<Make>,
    private val store: ViewModelStoreOwner,
    private val navController: NavController
) : RecyclerView.Adapter<MakeHolder>() {
    override fun getItemCount() = makeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return MakeHolder(view, store, navController)
    }

    override fun onBindViewHolder(holder: MakeHolder, position: Int) {
        val make = makeList[position]
        holder.setName(make.name)
        holder.setCount(make.count)
        holder.callImg(make.url, make.name)
    }
}