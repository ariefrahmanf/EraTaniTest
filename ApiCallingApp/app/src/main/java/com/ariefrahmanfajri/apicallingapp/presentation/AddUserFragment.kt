package com.ariefrahmanfajri.apicallingapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ariefrahmanfajri.apicallingapp.R
import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.databinding.FragmentAddUserBinding
import com.ariefrahmanfajri.apicallingapp.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddUserFragment : Fragment() {
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<UserViewModel>()
    private val nav by lazy { activity?.findNavController(R.id.main_content) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        binding.apply {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val gender = etGender.text.toString()
            val status = etStatus.text.toString()
            when {
                name.isNullOrEmpty() -> {
                    tlName.error = "Nama tidak boleh kosong"
                }
                email.isNullOrEmpty() -> {
                    tlEmail.error = "Email tidak boleh kosong"
                }
                gender.isNullOrEmpty() -> {
                    tlGender.error = "Gender tidak boleh kosong"
                }
                status.isNullOrEmpty() -> {
                    tlStatus.error = "Status tidak boleh kosong"
                }
                else -> {
                    tlEmail.error = null
                    tlGender.error = null
                    tlName.error = null
                    tlStatus.error = null

                    val body = UserBody(
                        name, email, gender, status
                    )

                    vm.createUser(body).observe(viewLifecycleOwner) {
                        when(it) {
                            is Resource.Success -> {
                                nav?.navigateUp()
                            }
                            is Resource.Failure -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val genderAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            resources.getStringArray(R.array.gender)
        )
        binding.etGender.setAdapter(genderAdapter)

        val statusAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            resources.getStringArray(R.array.status)
        )
        binding.etStatus.setAdapter(statusAdapter)
    }
}