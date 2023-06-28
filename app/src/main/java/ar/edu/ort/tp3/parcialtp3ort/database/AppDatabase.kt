package ar.edu.ort.tp3.parcialtp3ort.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.edu.ort.tp3.parcialtp3ort.entities.Car
import ar.edu.ort.tp3.parcialtp3ort.database.CarDao

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class appDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "PremierRentCarRoom"
                    ).allowMainThreadQueries().build() // No es lo mas recomendable que se ejecute en el mainthread
                }
            }
            return INSTANCE
        }

        fun getIntance(): appDatabase? {
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}