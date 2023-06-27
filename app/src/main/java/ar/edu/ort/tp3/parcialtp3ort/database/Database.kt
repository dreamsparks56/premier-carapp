package ar.edu.ort.tp3.parcialtp3ort.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.ContentObservable
import android.database.sqlite.SQLiteOpenHelper
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.Models.LogoResponse

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    var creationLogoQuery : String = "CREATE TABLE IF NOT EXISTS LOGO ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, abreviatura TEXT, imagenURL TEXT)"
    var creationCarQuery : String = "CREATE TABLE IF NOT EXISTS CAR ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, consumoCiudad REAL, descripcionAuto TEXT, consumoCombinado REAL, " +
            "motor INTEGER, cilindrada REAL, tipoConduccion TEXT, combustible TEXT, consumoAutopista REAL, marca TEXT, modelo TEXT, caja TEXT, ano TEXT, image TEXT)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(creationLogoQuery)
        db.execSQL(creationCarQuery)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS LOGO")
        db.execSQL("DROP TABLE IF EXISTS CAR")
        onCreate(db)
    }

    fun addLogos(logos : List<LogoResponse>){
        val db = this.writableDatabase
        val chk : List<LogoResponse> = getAllLogos()

        logos.forEach { logo ->
            if(chk.find { it.nombre == logo.nombre && it.abreviatura == logo.abreviatura && it.imagenURL == logo.imagenURL} == null ){
                val values = ContentValues()
                values.put("nombre", logo.nombre)
                values.put("abreviatura", logo.abreviatura)
                values.put("imagenURL", logo.imagenURL)
                db.insert("LOGO", null, values)
            }
        }
        db.close()
    }
    fun addCars(cars : List<CarResponse>){
        val db = this.writableDatabase
        val chk : List<CarResponse> = getAllCars()

        cars.forEach { car ->
            if(chk.find { it.modelo == car.modelo && it.marca == car.marca && it.combustible == car.combustible && it.ano == car.ano && it.motor == car.motor && it.cilindrada == car.cilindrada } == null ){
                val values = ContentValues()
                values.put("consumoCiudad", car.consumoCiudad)
                values.put("descripcionAuto", car.descripcionAuto)
                values.put("consumoCombinado", car.consumoCombinado)
                values.put("motor", car.motor)
                values.put("cilindrada", car.cilindrada)
                values.put("tipoConduccion", car.tipoConduccion)
                values.put("combustible", car.combustible)
                values.put("consumoAutopista", car.consumoAutopista)
                values.put("marca", car.marca)
                values.put("modelo", car.modelo)
                values.put("caja", car.caja)
                values.put("ano", car.ano)
                values.put("image", car.image)
                db.insert("CAR", null, values)
            }
        }
        db.close()
    }

    fun getAllCars(): List<CarResponse>{
        val carsList : MutableList<CarResponse> = emptyList<CarResponse>().toMutableList()
        val qry : String = "SELECT * FROM CAR Order by modelo"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)

        if (cursor.moveToFirst()) {
            do {
                val car =
                    CarResponse(cursor.getString(1).toDouble(),cursor.getString(2),cursor.getString(3).toInt(),cursor.getString(4).toInt(),cursor.getString(5).toDouble(),cursor.getString(6),cursor.getString(7),cursor.getString(8).toDouble(),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13) )
                carsList.add(car)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return carsList.toList()
    }

    fun getAllLogos(): List<LogoResponse>{
        val logoList : MutableList<LogoResponse> = emptyList<LogoResponse>().toMutableList()
        val qry : String = "SELECT * FROM LOGO Order by nombre"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)

        if (cursor.moveToFirst()) {
            do {
                val logo =
                    LogoResponse(cursor.getString(1),cursor.getString(2),cursor.getString(3))
                logoList.add(logo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return logoList.toList()
    }

    fun searchCars(searchVal : String, searchType : String): List<CarResponse>{
        val carsList : MutableList<CarResponse> = emptyList<CarResponse>().toMutableList()
        val qry : String = "SELECT * FROM CAR "
        val orderBy : String = "Order by modelo "
        var where : String = " "

        if(searchType == "combustible"){
            where = "WHERE combustible = '$searchVal' "
        }else if(searchType == "make"){
            where = "WHERE marca = '$searchVal' "
        }else if(searchType == "model"){
            where = "WHERE modelo = '$searchVal' "
        }
        val db = this.readableDatabase
        val finalQry : String = qry + where + orderBy
        val cursor = db.rawQuery(finalQry, null)
        if (cursor.moveToFirst()) {
            do {
                val car =
                    CarResponse(cursor.getString(1).toDouble(),cursor.getString(2),cursor.getString(3).toInt(),cursor.getString(4).toInt(),cursor.getString(5).toDouble(),cursor.getString(6),cursor.getString(7),cursor.getString(8).toDouble(),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13) )
                carsList.add(car)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return carsList.toList()
    }

    companion object{
        private val DATABASE_NAME = "PremierRentCar"
        private val DATABASE_VERSION = 1
        private lateinit var INSTANCE: DBHelper
        fun initDatabaseInstance(ctx: Context): DBHelper {
            INSTANCE = DBHelper(ctx,null)
            return INSTANCE
        }
        fun getIntance(): DBHelper {
            return INSTANCE
        }
    }
}
