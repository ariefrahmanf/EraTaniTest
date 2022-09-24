package com.ariefrahmanfajri.dataprocessapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.ariefrahmanfajri.dataprocessapp.R
import com.ariefrahmanfajri.dataprocessapp.data.local.ProductModel
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentAddProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : Fragment() {
    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!
    private val nav by lazy { activity?.findNavController(R.id.main_content) }
    private val vm by viewModel<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddProduct.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        binding.apply {
            when{
                binding.etName.text.isNullOrEmpty() -> {
                    tlName.error = "Name cannot be empty"
                }
                binding.etStock.text.isNullOrEmpty() -> {
                    tlStock.error = "Stock cannot be zero"
                }
                binding.etPrice.text.isNullOrEmpty() -> {
                    tlPrice.error = "Price cannot be zero"
                }
                binding.etDesc.text.isNullOrEmpty() -> {
                    tlDesc.error = "Description cannot be empty"
                }
                else -> {
                    val name = etName.text.toString()
                    val stock = etStock.text.toString().toInt()
                    val price = etPrice.text.toString().toInt()
                    val desc = etDesc.text.toString()

                    tlName.error = null
                    tlStock.error = null
                    tlPrice.error = null
                    tlDesc.error = null

                    vm.insertProduct(
                        ProductModel(
                            name, stock, desc, price
                        )
                    )
                    Toast.makeText(
                        requireContext(),
                        "Product successfully added",
                        Toast.LENGTH_SHORT
                    ).show()
                    nav?.navigateUp()
                }
            }
        }
    }

}