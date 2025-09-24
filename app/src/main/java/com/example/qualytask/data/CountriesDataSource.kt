package com.example.qualytask.data

import com.example.qualytask.R

interface CountriesDataSource{
    fun getCountries(): List<CountryCode>
}
class CountriesDataSourceImpl(): CountriesDataSource {
    override fun getCountries(): List<CountryCode> {
        return listOf(
            CountryCode(R.drawable.us, "United States", "+1"),
            CountryCode(R.drawable.eg, "Egypt", "+20"),
            CountryCode(R.drawable.gb, "United Kingdom", "+44"),
            CountryCode(R.drawable.`in`, "India", "+91"),
            CountryCode(R.drawable.ca, "Canada", "+1"),
            CountryCode(R.drawable.de, "Germany", "+49"),
            CountryCode(R.drawable.fr, "France", "+33"),
            CountryCode(R.drawable.sa, "Saudi Arabia", "+966"),
            CountryCode(R.drawable.jp, "Japan", "+81"),
            CountryCode(R.drawable.cn, "China", "+86"),
            CountryCode(R.drawable.br, "Brazil", "+55")
        )
    }
}