package com.ariefrahmanfajri.dataprocessapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.ariefrahmanfajri.dataprocessapp.R
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentDetailProductBinding
import com.ariefrahmanfajri.dataprocessapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProductFragment : Fragment() {
    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding!!
    private val nav by lazy { activity?.findNavController(R.id.main_content) }
    private val vm by viewModel<ProductViewModel>()
    private val args by navArgs<DetailProductFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            args.product.apply {
                tvTitle.text = name
                tvPrice.text = price.toString()
                tvDesc.text = desc
                tvStock.text = "Stok: $stock"
            }
        }

        binding.btnBuy.setOnClickListener {
            val qtt = binding.etQuantity.text.toString()
            if (qtt.isNotEmpty() || qtt != "") {
                if (qtt.toInt() > 0 && args.product.stock > qtt.toInt()) {
                    vm.updateProduct(args.product.stock-qtt.toInt(), args.product.id)

                    vm.getSpecifiedProduct(args.product.id).observe(viewLifecycleOwner) {
                        binding.tvStock.text = "Stok: ${it.stock}"
                    }
                }
                Toast.makeText(requireContext(), "Success buying ${args.product.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}