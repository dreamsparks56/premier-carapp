package ar.edu.ort.tp3.parcialtp3ort.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.ContentObservable
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import ar.edu.ort.tp3.parcialtp3ort.Models.LogoResponse
import java.lang.Exception

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    var creationLogoQuery : String = "CREATE TABLE IF NOT EXISTS LOGO ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, abreviatura TEXT, imagenURL TEXT)"
    var creationCarQuery : String = "CREATE TABLE IF NOT EXISTS CAR ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, consumoCiudad REAL, descripcionAuto TEXT, consumoCombinado REAL, " +
            "motor INTEGER, cilindrada REAL, tipoConduccion TEXT, combustible TEXT, consumoAutopista REAL, marca TEXT, modelo TEXT, caja TEXT, ano TEXT, image TEXT)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(creationLogoQuery)
        db.execSQL(creationCarQuery)
        //db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS LOGO")
        db.execSQL("DROP TABLE IF EXISTS CAR")
        onCreate(db)
    }

    fun addLogos(logos : List<LogoResponse>){
        val chk : List<LogoResponse> = getAllLogos()
        val db = this.writableDatabase
        //db.enableWriteAheadLogging()
        logos.forEach { logo ->
            if(chk.isNullOrEmpty() || chk.find { it.nombre == logo.nombre && it.abreviatura == logo.abreviatura && it.imagenURL == logo.imagenURL} == null ){
                val values = ContentValues()
                val nom :String = if (logo.nombre.isNullOrEmpty()) "" else logo.nombre
                val abrev :String = if (logo.abreviatura.isNullOrEmpty()) "" else logo.abreviatura
                val imageLink :String = if (logo.imagenURL.isNullOrEmpty()) "" else logo.imagenURL.toString()
                values.put("nombre", nom)
                values.put("abreviatura", abrev)
                values.put("imagenURL", imageLink)
                db.insert("LOGO", null, values)
            }
        }
        db.close()
    }
    fun addCars(cars : List<CarResponse>){
        val chk : List<CarResponse> = getAllCars()
        val db = this.writableDatabase
        //db.enableWriteAheadLogging()
        cars.forEach { car ->
            if(chk.isNullOrEmpty() || chk.find{ it.modelo == car.modelo && it.marca == car.marca && it.combustible == car.combustible && it.ano == car.ano && it.motor == car.motor && it.cilindrada == car.cilindrada } == null ){
                val values = ContentValues()
                val cComb :Int = if (car.consumoCombinado.toString().isNullOrEmpty()) 0 else car.consumoCombinado
                val mot :Int = if (car.motor.toString().isNullOrEmpty()) 0 else car.motor
                val cCiudad :Double = if (car.consumoCiudad.toString().isNullOrEmpty()) 0.0 else car.consumoCiudad
                val cCilindrada :Double = if (car.cilindrada.toString().isNullOrEmpty()) 0.0 else car.cilindrada
                val cAutopista :Double = if (car.consumoAutopista.toString().isNullOrEmpty()) 0.0 else car.consumoAutopista
                val descAuto :String = if (car.descripcionAuto.isNullOrEmpty()) "" else car.descripcionAuto
                val tConduccion :String = if (car.tipoConduccion.isNullOrEmpty()) "" else car.tipoConduccion
                val tCombustible :String = if (car.combustible.isNullOrEmpty()) "" else car.combustible
                val nMarca :String = if (car.marca.isNullOrEmpty()) "" else car.marca
                val nModelo :String = if (car.modelo.isNullOrEmpty()) "" else car.modelo
                val tCaja :String = if (car.caja.isNullOrEmpty()) "" else car.caja
                val fecAno :String = if (car.ano.isNullOrEmpty()) "" else car.ano
                val imageLink :String = if (car.image.toString().isNullOrEmpty()) "" else car.image.toString()
                values.put("consumoCiudad", cCiudad)
                values.put("descripcionAuto", descAuto)
                values.put("consumoCombinado", cComb)
                values.put("motor", mot)
                values.put("cilindrada", cCilindrada)
                values.put("tipoConduccion", tConduccion)
                values.put("combustible", tCombustible)
                values.put("consumoAutopista", cAutopista)
                values.put("marca", nMarca)
                values.put("modelo", nModelo)
                values.put("caja", tCaja)
                values.put("ano", fecAno)
                values.put("image", imageLink)
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
        try {
            if (cursor.moveToFirst()) {
                do {
                    val car =
                        CarResponse(cursor.getDouble(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getDouble(5),cursor.getString(6),cursor.getString(7),cursor.getDouble(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13) )
                    carsList.add(car)
                } while (cursor.moveToNext())
            }
        }catch (ex:Exception){
            Log.i("",ex.message.toString())
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
        try {
            if (cursor.moveToFirst()) {
                do {
                    val logo =
                        LogoResponse(cursor.getString(1),cursor.getString(2),cursor.getString(3))
                    logoList.add(logo)
                } while (cursor.moveToNext())
            }
        }catch (ex:Exception){
            Log.i("",ex.message.toString())
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
            where = "WHERE combustible like '%' || ? || '%' "
        }else if(searchType == "make"){
            where = "WHERE marca like '%' || ? || '%' "
        }else if(searchType == "model"){
            where = "WHERE modelo like '%' || ? || '%' "
        }
        val finalQry : String = qry + where + orderBy
        val selectionArgs : Array<String> = arrayOf(searchVal)
        val db = this.readableDatabase
        val cursor = db.rawQuery(finalQry, selectionArgs)
        try {
            if (cursor.moveToFirst()) {
                do {
                    val car =
                        CarResponse(cursor.getDouble(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getDouble(5),cursor.getString(6),cursor.getString(7),cursor.getDouble(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13) )
                        //CarResponse(cursor.getString(1).toDouble(),cursor.getString(2),cursor.getString(3).toInt(),cursor.getString(4).toInt(),cursor.getString(5).toDouble(),cursor.getString(6),cursor.getString(7),cursor.getString(8).toDouble(),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13) )
                    carsList.add(car)
                } while (cursor.moveToNext())
            }
        }catch (ex:Exception){
            Log.i("",ex.message.toString())
        }
        cursor.close()
        db.close()
        return carsList.toList()
    }

    companion object{
        private val DATABASE_NAME = "PremierRentCar"
        private val DATABASE_VERSION = 6
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
