package com.example.sqlitepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.sqlitepractice.data.City
import com.example.sqlitepractice.data.Country
import com.example.sqlitepractice.data.WorldDatabase
import com.example.sqlitepractice.data.relations.CountryWithCities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var btAdd: Button
    private lateinit var btGet: Button

    private lateinit var worldData: List<CountryWithCities>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = WorldDatabase.getInstance(this).worldDao

        btAdd = findViewById(R.id.btAdd)
        btAdd.setOnClickListener {
            CoroutineScope(IO).launch {

                dao.addCountry(Country(0,"ARE","United Arab Emirates","Asia","Middle East",83600.00f,1971,2441000,74.1f,37966.00f,36846.00f,"Al-Imarat al-Â´Arabiya al-Muttahida","Emirate Federation","Zayid bin Sultan al-Nahayan",65,"AE"))
                dao.addCity(City(0,"Dubai","ARE","Dubai",669181,1))
                dao.addCity(City(0,"Abu Dhabi","ARE","Abu Dhabi",398695,1))
                dao.addCity(City(0,"Al Ain","ARE","Abu Dhabi",225970,1))
            }
        }

        btGet = findViewById(R.id.btGet)
        btGet.setOnClickListener {
            CoroutineScope(IO).launch {
                worldData = dao.getCountryWithCities()
                for(city:CountryWithCities in worldData){
                    Log.e("MainActivityLOG", "DATA: ${city.cities}")
                }
            }
        }
    }
}