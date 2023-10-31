package com.example.front.navigation

sealed class Screen(val route:String){
    object Login:Screen(route = "login_screen")
    object Home:Screen(route = "home_screen")
    object SplashScreen: Screen(route = "splash_screen")
    object Intro1:Screen(route = "intro1")
    object Intro2:Screen(route="intro2")
    object Intro3:Screen(route="intro3")
    object Intro4:Screen(route="intro4")
}