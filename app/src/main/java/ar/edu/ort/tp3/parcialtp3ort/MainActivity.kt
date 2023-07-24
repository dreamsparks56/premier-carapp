package ar.edu.ort.tp3.parcialtp3ort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.edu.ort.tp3.parcialtp3ort.database.appDatabase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Probando repo git
        //DBHelper.initDatabaseInstance(getApplicationContext())
        appDatabase.getAppDataBase(getApplicationContext())

        //val db : DBHelper = DBHelper(getApplicationContext(),null)


    }



   }