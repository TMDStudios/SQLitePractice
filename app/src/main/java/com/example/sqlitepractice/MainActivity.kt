package com.example.sqlitepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.sqlitepractice.data.City
import com.example.sqlitepractice.data.Country
import com.example.sqlitepractice.data.WorldDao
import com.example.sqlitepractice.data.WorldDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvTop: TextView
    private lateinit var tvBottom: TextView

    private lateinit var dao: WorldDao
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queries = resources.getStringArray(R.array.queries)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, queries)
        autoCompleteTextView = findViewById(R.id.dropdownItems)
        autoCompleteTextView.setAdapter(arrayAdapter)
        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            tvTop.text = queries[i]
            CoroutineScope(IO).launch {
                val countryData = async { dao.getCountries() }.await()
                if(countryData.isNotEmpty()){
                    withContext(Main){
                        val countryId = Random.nextInt(countryData.size-1)
                        tvBottom.text = "Cities: ${dao.query1()}"
                    }
                }else{
                    withContext(Main){
                        tvBottom.text = "No data found"
                    }
                }
            }
        }

        dao = WorldDatabase.getInstance(this).worldDao
        checkDatabase()

        tvTop = findViewById(R.id.tvTop)
        tvBottom = findViewById(R.id.tvBottom)
    }

    private fun checkDatabase(){
        Log.d("MAIN", "checking db")
        CoroutineScope(IO).launch {
            val countryData = async { dao.getCountries() }.await()
            if(countryData.isEmpty()){
                readJSON()
            }else{
                Log.d("MAIN", "DB data has already been loaded")
                withContext(Main){
                    tvTop.text = "Ready"
                }
            }
        }
    }

    private fun readJSON(){
        Log.d("MAIN", "reading JSON")
        CoroutineScope(IO).launch {

            val countryData = async { fetchData("countriesJSON.json") }.await()
            if(countryData.isNotEmpty()){
                populateCountriesDB(countryData)
            }else{
                Log.d("MAIN", "Unable to get countryData")
            }

            val cityData = async { fetchData("citiesJSON.json") }.await()
            if(cityData.isNotEmpty()){
                populateCitiesDB(cityData)
            }else{
                Log.d("MAIN", "Unable to get cityData")
            }
        }
    }

    private fun fetchData(file: String): String{
        return applicationContext.assets.open(file).bufferedReader().use { it.readText() }
    }

    private suspend fun populateCountriesDB(result: String){
        Log.d("MAIN", "POPULATING countries")
        withContext(Main){
            val jsonArray = JSONArray(result)

            for(i in 0 until jsonArray.length()){
                val code = jsonArray.getJSONObject(i).getString("code")
                val name = jsonArray.getJSONObject(i).getString("name")
                val continent = jsonArray.getJSONObject(i).getString("continent")
                val region = jsonArray.getJSONObject(i).getString("region")
                val surfaceArea = jsonArray.getJSONObject(i).getString("surface_area")
                val indepYear = jsonArray.getJSONObject(i).getString("indep_year")
                val population = jsonArray.getJSONObject(i).getString("population")
                val lifeExpectancy = jsonArray.getJSONObject(i).getString("life_expectancy")
                val gnp = jsonArray.getJSONObject(i).getString("gnp")
                val gnpOld = jsonArray.getJSONObject(i).getString("gnp_old")
                val localName = jsonArray.getJSONObject(i).getString("local_name")
                val governmentForm = jsonArray.getJSONObject(i).getString("government_form")
                val headOfState = jsonArray.getJSONObject(i).getString("head_of_state")
                val capital = jsonArray.getJSONObject(i).getString("capital")
                val code2 = jsonArray.getJSONObject(i).getString("code2")
                dao.addCountry(Country(
                    0,
                    code,
                    name,
                    continent,
                    region,
                    convertToFloat(surfaceArea),
                    convertToInt(indepYear),
                    convertToInt(population),
                    convertToFloat(lifeExpectancy),
                    convertToFloat(gnp),
                    convertToFloat(gnpOld),
                    localName,
                    governmentForm,
                    headOfState,
                    convertToInt(capital),
                    code2))
            }
        }
    }

    private suspend fun populateCitiesDB(result: String){
        Log.d("MAIN", "POPULATING cities")
        withContext(Main){
            val jsonArray = JSONArray(result)

            for(i in 0 until jsonArray.length()){
                val name = jsonArray.getJSONObject(i).getString("name")
                val countryCode = jsonArray.getJSONObject(i).getString("country_code")
                val district = jsonArray.getJSONObject(i).getString("district")
                val population = jsonArray.getJSONObject(i).getString("population")
                val countryId = jsonArray.getJSONObject(i).getString("country_id")
                dao.addCity(City(
                    0,
                    name,
                    countryCode,
                    district,
                    convertToInt(population),
                    convertToInt(countryId)))
            }
            tvTop.text = "Ready"
        }
    }

    private fun convertToInt(string: String): Int?{
        try{
            return string.toInt()
        }catch(e: NumberFormatException){

        }
        return null
    }

    private fun convertToFloat(string: String): Float?{
        try{
            return string.toFloat()
        }catch(e: NumberFormatException){

        }
        return null
    }
}