package ar.edu.ort.tp3.parcialtp3ort.tools

import android.view.View
import android.widget.ImageView
import ar.edu.ort.tp3.parcialtp3ort.entities.Make
import com.bumptech.glide.Glide

class ImageFetching {

    companion object {
        fun getImageManaged(view: View, element: ImageView, path: String) {
            val ERROR_DEFAULT_PATH = "international"

            Glide
                .with(view)
                .load(Make.Constants.getImage(path)).fitCenter()
                .error(
                    Glide
                        .with(view)
                        .load(Make.Constants.getImage(ERROR_DEFAULT_PATH))
                )
                .into(element)
        }
    }

}