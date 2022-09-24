package com.ariefrahmanfajri.apicallingapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefrahmanfajri.apicallingapp.R
import com.ariefrahmanfajri.apicallingapp.databinding.FragmentHomeBinding
import com.ariefrahmanfajri.apicallingapp.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<UserViewModel>()
    private val nav by lazy { activity?.findNavController(R.id.main_content) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddUser.setOnClickListener {
            nav?.navigate(HomeFragmentDirections.actionHomeFragmentToAddUserFragment())
        }

        vm.getAllUser().observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    it.data?.let {
                        val userAdapter = UserAdapter(it)
                        binding.apply {
                            rvUser.apply {
                                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                                adapter = userAdapter
                            }
                        }
                    }
                }
                is Resource.Failure -> {

                }
            }
        }
    }
}