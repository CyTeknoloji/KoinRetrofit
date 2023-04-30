package com.atilsamancioglu.koinretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atilsamancioglu.koinretrofit.R
import com.atilsamancioglu.koinretrofit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fragmentFactory : MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}