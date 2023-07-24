package ar.edu.ort.tp3.parcialtp3ort.holders

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.R

class CategoryHolder(v: View) : RecyclerView.ViewHolder(v) {

    private var view: View

    init {
        this.view = v
    }

    fun setName(name: String) {
        val category = this.view.findViewById<TextView>(R.id.categoryTitle)
        category.text = name
    }

    fun getCardLayout(): CardView {
        return view.findViewById(R.id.categoryCardBase)
    }

    fun setImage(image: Drawable, category: String?) {
        val imageView = this.view.findViewById<ImageView>(R.id.categoryImage)
        imageView.setImageDrawable(image)
        imageView.contentDescription = category
    }
}