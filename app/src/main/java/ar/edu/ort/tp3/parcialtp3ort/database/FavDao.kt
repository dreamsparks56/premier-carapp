package ar.edu.ort.tp3.parcialtp3ort.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Favorito

@Dao
interface FavDao {

        @Insert(onConflict = OnConflictStrategy.ABORT) //Para q lance un expeción si hay error en este conflicto.
        fun insertFav(fav: Favorito?)

        @Delete
        fun deleteFav(fav: Favorito?)

        @Query("SELECT * FROM favoritos ORDER BY mail desc ")
        fun getAllCars(): MutableList<Favorito>?

        @Query("SELECT * FROM favoritos where mail == :mailUsuario ORDER BY mail desc ")
        fun getAllCars(mailUsuario:String): MutableList<Favorito>?

}