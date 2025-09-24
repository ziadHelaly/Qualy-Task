package com.example.qualytask.data

interface CountriesRepository {
    fun getCountriesCodes(): List<CountryCode>
}

class CountriesCodesRepositoryImpl(private val dataSource: CountriesDataSource): CountriesRepository{
    override fun getCountriesCodes(): List<CountryCode> {
        return dataSource.getCountries()
    }

}