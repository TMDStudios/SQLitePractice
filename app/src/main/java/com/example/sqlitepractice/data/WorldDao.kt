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

    @Query("SELECT name, language, percentage FROM countries\n" +
            "INNER JOIN languages\n" +
            "ON countries.id = languages.countryId\n" +
            "WHERE language=\"Slovene\"\n" +
            "ORDER BY percentage DESC;")
    suspend fun query2(): List<Query2>

    /*What query would you run to display the total number of cities for each country? Your
    query should return the name of the country and the total number of cities. Your query
    should arrange the result by the number of cities in descending order.*/

    @Query("SELECT countries.name, sum((case when countries.id = cities.countryId then 1 else 0 end)) as 'cities' FROM countries\n" +
            "INNER JOIN cities\n" +
            "ON countries.id = cities.countryId\n" +
            "GROUP BY countries.id\n" +
            "ORDER BY sum((case when countries.id = cities.countryId then 1 else 0 end)) DESC;")
    suspend fun query3(): List<Query3>

    /*What query would you run to get all the cities in Mexico with a population of greater
    than 500,000? Your query should arrange the result by population in descending order.*/

    @Query("SELECT cities.name, cities.population FROM countries\n" +
            "INNER JOIN cities\n" +
            "ON countries.id = cities.countryId\n" +
            "WHERE countries.name = 'Mexico' AND cities.population > 500000\n" +
            "ORDER BY cities.population DESC;")
    suspend fun query4(): List<Query4>

    /*What query would you run to get all languages in each country with a percentage greater
    than 89%? Your query should arrange the result by percentage in descending order.*/

    @Query("SELECT name, language, percentage FROM countries\n" +
            "INNER JOIN languages\n" +
            "ON countries.id = languages.countryId\n" +
            "WHERE percentage > 89\n" +
            "ORDER BY percentage DESC;")
    suspend fun query5(): List<Query5>

    /*What query would you run to get countries with only Constitutional Monarchy with a surface
    area of more than 200 and a life expectancy greater than 75 years?*/

    @Query("SELECT name, governmentForm, surfaceArea, lifeExpectancy FROM countries\n" +
            "WHERE governmentForm = 'Constitutional Monarchy' AND surfaceArea > 200 AND lifeExpectancy > 75;")
    suspend fun query6(): List<Query6>

    /*What query would you run to get all the cities of Argentina inside the Buenos Aires
    district and have the population greater than 500, 000? The query should return the
    City Name, District, and Population.*/

    @Query("SELECT cities.name, cities.district, cities.population FROM countries\n" +
            "INNER JOIN cities\n" +
            "ON countries.id = cities.countryId\n" +
            "WHERE countries.name = 'Argentina' AND district = 'Buenos Aires' AND cities.population > 500000\n" +
            "ORDER BY cities.population DESC;")
    suspend fun query7(): List<Query7>

    /*What query would you run to summarize the number of countries in each region? The query
    should display the name of the region and the number of countries. Also, the query should
    arrange the result by the number of countries in descending order.*/

    @Query("SELECT region, sum((case when region = region then 1 else 0 end)) as 'countries' \n" +
            "FROM countries \n" +
            "GROUP BY region\n" +
            "ORDER BY sum((case when region = region then 1 else 0 end)) DESC;")
    suspend fun query8(): List<Query8>
}