package com.example.sqlitepractice.data

import androidx.room.*
import com.example.sqlitepractice.data.relations.CountryWithCities

@Dao
interface WorldDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addCity(city: City)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addCountry(country: Country)

    @Query("SELECT * FROM cities ORDER BY id ASC")
    fun getCities(): List<City>

    @Query("SELECT * FROM countries ORDER BY id ASC")
    fun getCountries(): List<Country>

    @Transaction
    @Query("SELECT * FROM countries WHERE id = :countryId")
    suspend fun getCountryWithCities(countryId: Int): List<CountryWithCities>
}