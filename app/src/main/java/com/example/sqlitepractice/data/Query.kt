package com.example.sqlitepractice.data

// We need to create a class for each query as they only return certain columns from our table
// In this query, we are looking for the name, population, and surface area of countries
// Pay attention to the names of the variables as well as the data types

data class Query1(
    val name: String,
    val population: Int,
    val surfaceArea: Float
)

data class Query2(
    val name: String,
    val language: String,
    val percentage: Float
)

data class Query3(
    val name: String,
    val cities: Int
)

data class Query4(
    val name: String,
    val population: Int
)

data class Query5(
    val name: String,
    val language: String,
    val percentage: Float
)

data class Query6(
    val name: String,
    val governmentForm: String,
    val surfaceArea: Float,
    val lifeExpectancy: Float
)

data class Query7(
    val name: String,
    val district: String,
    val population: Int
)

data class Query8(
    val region: String,
    val countries: Int
)