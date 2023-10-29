    package com.example.front

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.front.app.PostOfficeApp
import com.example.front.model.LoginDTO
import com.example.front.repository.Repository
import com.example.front.ui.theme.FrontTheme
import com.example.front.viewmodels.login.LoginViewModel
import com.example.front.viewmodels.login.MainViewModelFacotry
import com.example.front.views.RegistrationCategories
import com.example.front.views.SplashScreen

    class MainActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostOfficeApp();
            val repository = Repository()
            val viewModelFactory = MainViewModelFacotry(repository)
            viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
            var data = LoginDTO("marija.andric","MejoSmrdi123!")
            viewModel.getLoginnInfo(data)
            val response = viewModel.myResponse.value
            Log.e("RESPONSEEE",response.toString())
        }
    }
}


@Composable
fun SplashScreenAndIntro() {
    SplashScreen().Navigation()
}
