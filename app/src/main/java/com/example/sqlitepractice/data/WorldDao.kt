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

    @Query("SELECT * FROM languages ORDER BY id ASC")
    suspend fun getLanguages(): List<Language>

    @Query("SELECT * FROM countries WHERE id = :countryId")
    suspend fun getCountry(countryId: Int): List<Country>

    //Advanced Queries

    /*What query would you run to get all the countries with Surface Area below 501
    and Population greater than 100,000?*/

    @Query("SELECT name, surfaceArea, population FROM countries\n" +
            "WHERE surfaceArea < 501 AND population > 100000;")
    suspend fun query1(): List<Query1>

    /*What query would you run to get all the countries that speak Slovene? Your query should
    return the name of the country, language, and language percentage. Your query should arrange
    the result by language percentage in descending order.*/

    /*What query would you run to display the total number of cities for each country? Your
    query should return the name of the country and the total number of cities. Your query
    should arrange the result by the number of cities in descending order.*/

    /*What query would you run to get all the cities in Mexico with a population of greater
    than 500,000? Your query should arrange the result by population in descending order.*/

    /*What query would you run to get all languages in each country with a percentage greater
    than 89%? Your query should arrange the result by percentage in descending order.*/

    /*What query would you run to get countries with only Constitutional Monarchy with a surface
    area of more than 200 and a life expectancy greater than 75 years?*/

    /*What query would you run to get all the cities of Argentina inside the Buenos Aires
    district and have the population greater than 500, 000? The query should return the
    Country Name, City Name, District, and Population.*/

    /*What query would you run to summarize the number of countries in each region? The query
    should display the name of the region and the number of countries. Also, the query should
    arrange the result by the number of countries in descending order.*/
}