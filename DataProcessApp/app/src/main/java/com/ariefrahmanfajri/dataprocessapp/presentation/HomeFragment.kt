package com.ariefrahmanfajri.dataprocessapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefrahmanfajri.dataprocessapp.R
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentHomeBinding
import com.ariefrahmanfajri.dataprocessapp.presentation.adapter.ProductListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val nav by lazy { activity?.findNavController(R.id.main_content) }
    private val vm by viewModel<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getAllProduct()
            .observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    val productAdapter = ProductListAdapter(it) {
                        nav?.navigate(HomeFragmentDirections.actionHomeFragmentToDetailProductFragment(it))
                    }

                    binding.rvProduct.apply {
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        adapter = productAdapter
                    }
                } else {
                    Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show()
                }
            }
    }
}