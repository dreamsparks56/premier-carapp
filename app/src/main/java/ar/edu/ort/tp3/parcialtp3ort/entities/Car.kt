package ar.edu.ort.tp3.parcialtp3ort.entities


class Car() {


    companion object {
        val THUMB_IMAGE_DIRECTION: String = "https://raw.githubusercontent.com/filippofilip95/car-logos-dataset/master/logos/thumb/%s.png"
        fun getImage(carMake: String): String {
            return String.format(THUMB_IMAGE_DIRECTION, carMake)
        }
    }
}