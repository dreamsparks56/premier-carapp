package ar.edu.ort.tp3.parcialtp3ort.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ar.edu.ort.tp3.parcialtp3ort.entities.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(car: Car?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cars: MutableList<Car>?)

    @Update
    fun updateCar(car: Car?)

    @Delete
    fun delete(car: Car?)

    @Query("SELECT * FROM cars ORDER BY favorito desc, modelo ")
    fun getAllCars(): MutableList<Car>?

    @Query("SELECT * FROM cars WHERE combustible like '%' || :combustible || '%'  ORDER BY favorito desc, modelo")
    fun getCarsByCombustible(combustible : String ): MutableList<Car>?

    @Query("SELECT * FROM cars WHERE marca like '%' || :marca || '%'  ORDER BY favorito desc, modelo")
    fun getCarsByMarca(marca : String ): MutableList<Car>?

    @Query("SELECT * FROM cars WHERE modelo like '%' || :modelo || '%'  ORDER BY favorito desc, modelo")
    fun getCarsByModelo(modelo : String ): MutableList<Car>?

    @Query("SELECT * FROM cars WHERE favorito = :favorite  ORDER BY favorito desc, modelo")
    fun getFavoriteCars(favorite: Boolean): MutableList<Car>?

}