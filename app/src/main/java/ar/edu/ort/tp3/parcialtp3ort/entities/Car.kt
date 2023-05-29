package ar.edu.ort.tp3.parcialtp3ort.entities


class Car() {


    companion object {

        fun getFullDriveName(drive: String?): String {
            val fullDrive: String
            when(drive) {
                "2wd" -> fullDrive = "Front Wheel"
                "rwd" -> fullDrive = "Rear Wheel"
                "awd" -> fullDrive = "All Wheel"
                "4wd" -> fullDrive = "Four Wheel"
                else -> {
                    fullDrive = "Unspecified"
                }
            }
            return fullDrive
        }
    }
}