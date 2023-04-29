package com.atilsamancioglu.koinretrofit

import android.app.Application
import com.atilsamancioglu.koinretrofit.di.anotherModule
import com.atilsamancioglu.koinretrofit.di.appModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

}