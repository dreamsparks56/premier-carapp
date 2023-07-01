package ar.edu.ort.tp3.parcialtp3ort.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "favoritos",
    primaryKeys = ["mail","idAuto"],
    foreignKeys = [ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["idAuto"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Favorito(mail:String, idAuto:Int) {

     var mail: String


    var idAuto: Int = idAuto

    init{
        this.mail = mail
        this.idAuto = idAuto
    }

}