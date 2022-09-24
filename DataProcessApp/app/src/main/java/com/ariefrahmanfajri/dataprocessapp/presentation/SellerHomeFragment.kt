package com.ariefrahmanfajri.dataprocessapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ariefrahmanfajri.dataprocessapp.R
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentAddProductBinding
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentSellerHomeBinding

class SellerHomeFragment : Fragment() {
    private var _binding: FragmentSellerHomeBinding? = null
    private val binding get() = _binding!!
    private val nav by lazy { activity?.findNavController(R.id.main_content) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddProduct.setOnClickListener {
            nav?.navigate(SellerHomeFragmentDirections.actionSellerHomeFragmentToAddProductFragment())
        }

        binding.btnSeeStock.setOnClickListener {
            nav?.navigate(SellerHomeFragmentDirections.actionSellerHomeFragmentToSellerProductStockFragment())
        }
    }


}