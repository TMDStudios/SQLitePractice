package com.example.sqlitepractice.data

import androidx.room.*

@Dao
interface WorldDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addCity(city: City)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addCountry(country: Country)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addLanguage(language: Language)

    @Query("SELECT * FROM cities ORDER BY id ASC")
    fun getCities(): List<City>

    @Query("SELECT * FROM countries ORDER BY id ASC")
    fun getCountries(): List<Country>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCity(cityId: Int): List<City>

    @Query("SELECT * FROM countries WHERE id = :countryId")
    suspend fun getCountry(countryId: Int): List<Country>

    //Advanced Queries

    /*What query would you run to get all the countries with Surface Area below 501
    and Population greater than 100,000?*/

    @Query("SELECT name, surfaceArea, population FROM countries\n" +
            "WHERE surfaceArea < 501 AND population > 100000;")
    suspend fun query1(): List<Query1>
}