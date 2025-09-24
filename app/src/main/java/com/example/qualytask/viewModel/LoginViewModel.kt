package com.example.qualytask.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qualytask.R
import com.example.qualytask.data.CountriesRepository
import com.example.qualytask.data.CountryCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(private val repository: CountriesRepository) : ViewModel() {
    private var _countries: MutableStateFlow<List<CountryCode>> = MutableStateFlow(emptyList())
    var countries = _countries.asStateFlow()
    private var _selectedCountry: MutableStateFlow<CountryCode> =
        MutableStateFlow(CountryCode(R.drawable.gb, "United Kingdom", "+44"))
    var selectedCountry = _selectedCountry.asStateFlow()

    init {
        _countries.value = repository.getCountriesCodes()
    }

    fun updateSelectedCountry(countryCode: CountryCode) {
        _selectedCountry.value = countryCode
    }
}
class LoginViewModelFactory(private val repository: CountriesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}