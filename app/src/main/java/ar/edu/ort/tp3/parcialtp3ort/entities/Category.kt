package ar.edu.ort.tp3.parcialtp3ort.entities

import android.graphics.drawable.Drawable

class Category(name: String?, color: Int?, image: Drawable?, type: String?, field: String?) {
    var name: String = ""
    var color: Int
    var image: Drawable
    var type: String = ""
    var field: String = ""

    init {
        this.name = name!!
        this.color = color!!
        this.image = image!!
        this.type = type!!
        this.field = field!!
    }

}