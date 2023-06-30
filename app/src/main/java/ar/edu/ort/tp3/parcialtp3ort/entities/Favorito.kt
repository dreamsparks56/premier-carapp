package ar.edu.ort.tp3.parcialtp3ort.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "favoritos",
    foreignKeys = [ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["idAuto"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Favorito(mail:String, idAuto:Int) {
    @PrimaryKey
    @ColumnInfo(name = "mail")
    lateinit var mail: String

    @ColumnInfo(name = "idAuto")
    var idAuto: Int = idAuto
}