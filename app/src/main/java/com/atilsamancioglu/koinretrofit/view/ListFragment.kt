package com.atilsamancioglu.koinretrofit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.koinretrofit.databinding.FragmentListBinding
import com.atilsamancioglu.koinretrofit.model.CryptoModel
import com.atilsamancioglu.koinretrofit.service.CryptoAPI
import com.atilsamancioglu.koinretrofit.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(),RecyclerViewAdapter.Listener {

    private var _binding: FragmentListBinding? = null
    private val binding get()= _binding!!
    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(),this)

    @Inject
    lateinit var viewModel: CryptoViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getDataFromAPI()
        observeLiveData()


    }

    fun observeLiveData() {
        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {cryptos ->

            cryptos?.let {
                binding.recyclerView.visibility = View.VISIBLE
                cryptoAdapter = RecyclerViewAdapter(ArrayList(cryptos.data ?: arrayListOf()),this@ListFragment)
                binding.recyclerView.adapter = cryptoAdapter
            }

        })

        viewModel.cryptoError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it.data == true) {
                    binding.cryptoErrorText.visibility = View.VISIBLE
                } else {
                    binding.cryptoErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it.data == true) {
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.cryptoErrorText.visibility = View.GONE
                } else {
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })

    }

        override fun onDestroyView() {
        super.onDestroyView()
            //job?.cancel()
            _binding = null
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(),"Clicked on: ${cryptoModel.currency}",Toast.LENGTH_SHORT).show()
    }
}