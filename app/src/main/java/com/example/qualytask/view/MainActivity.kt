package com.example.qualytask.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.qualytask.data.CountriesCodesRepositoryImpl
import com.example.qualytask.data.CountriesDataSourceImpl
import com.example.qualytask.data.CountriesRepository
import com.example.qualytask.view.ui.theme.QualyTaskTheme
import com.example.qualytask.viewModel.LoginViewModel
import com.example.qualytask.viewModel.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QualyTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: LoginViewModel = ViewModelProvider(
                        this, LoginViewModelFactory(
                            CountriesCodesRepositoryImpl(CountriesDataSourceImpl())
                        )
                    ).get(LoginViewModel::class.java)
                    LoginScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

