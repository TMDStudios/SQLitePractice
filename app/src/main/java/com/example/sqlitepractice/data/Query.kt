package com.example.sqlitepractice.data

// We need to create a class for each query as they only return certain columns from our table
// In this query, we are looking for the name, population, and surface area of countries
// Pay attention to the names of the variables as well as the data types

data class Query1(
    val name: String,
    val population: Int,
    val surfaceArea: Float
)