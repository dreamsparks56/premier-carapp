package ar.edu.ort.tp3.parcialtp3ort.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.entities.Favorito

@Database(entities = [Car::class, Favorito::class], version = 2, exportSchema = false)
abstract class appDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
    abstract fun favDao(): FavDao

    companion object {

        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "PremierRentCarRoom"
                    )
                        .fallbackToDestructiveMigration() // Permitir migraciones destructivas xq cambie la version de la BD.
                        .allowMainThreadQueries()
                        .build() // No es lo mas recomendable que se ejecute en el mainthread

                }
            }
            return INSTANCE
        }

        fun getInstance(): appDatabase? {
            return INSTANCE
        }

        /*fun destroyDataBase() {
            INSTANCE = null
        }*/
    }
}