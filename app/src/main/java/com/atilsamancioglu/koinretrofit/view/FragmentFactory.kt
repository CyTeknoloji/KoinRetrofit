package com.atilsamancioglu.koinretrofit.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MainFragmentFactory @Inject constructor() : FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ListFragment::class.java.name->ListFragment()
            else -> super.instantiate(classLoader, className)
        }

    }

}