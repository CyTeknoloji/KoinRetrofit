package com.atilsamancioglu.koinretrofit.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.atilsamancioglu.koinretrofit.repository.CryptoDownload
import com.atilsamancioglu.koinretrofit.repository.CryptoDownloadImpl
import com.atilsamancioglu.koinretrofit.service.CryptoAPI
import com.atilsamancioglu.koinretrofit.viewmodel.CryptoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn()
@Module
class AppModule {

    @Singleton
    @Provides
    fun injectRetrofit() : CryptoAPI {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectCryptoDownload(api : CryptoAPI) = CryptoDownloadImpl(api) as CryptoDownload

    @Singleton
    @Provides
    fun injectViewModel(owner : ViewModelStoreOwner ) = ViewModelProvider(owner)[CryptoViewModel::class.java] as ViewModel

}