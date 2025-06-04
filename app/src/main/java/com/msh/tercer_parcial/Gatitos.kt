package com.msh.tercer_parcial.ui.gatitos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager

import com.msh.tercer_parcial.ui.CatApi
import com.msh.tercer_parcial.ui.CatAdapter

import com.msh.tercer_parcial.databinding.FragmentGatitosBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GatitosFragment : Fragment() {

    private var _binding: FragmentGatitosBinding? = null
    private val binding get() = _binding!!

    private lateinit var catAdapter: CatAdapter
    private lateinit var catApi: CatApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGatitosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupApi()
        loadCats()
    }

    private fun setupRecyclerView() {
        catAdapter = CatAdapter() // Si tu adapter requiere contexto, usa: CatAdapter(requireContext())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = catAdapter
        }
    }

    private fun setupApi() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catApi = retrofit.create(CatApi::class.java)
    }

    private fun loadCats() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val cats = catApi.getCatImages()
                catAdapter.updateCats(cats)
            } catch (e: Exception) {
                e.printStackTrace()
                // Podrías mostrar un mensaje de error con Toast o Snackbar
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

