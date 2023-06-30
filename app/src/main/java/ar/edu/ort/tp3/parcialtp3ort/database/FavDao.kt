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

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertFav(fav: Favorito?)

        @Delete
        fun deleteFav(fav: Favorito?)

        @Query("SELECT * FROM favoritos ORDER BY id desc ")
        fun getAllCars(): MutableList<Favorito>?

}