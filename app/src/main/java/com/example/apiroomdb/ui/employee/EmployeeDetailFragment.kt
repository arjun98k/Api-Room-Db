package com.example.apiroomdb.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apiroomdb.data.local.AppDatabase
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.databinding.FragmentEmployeeDetailBinding
import com.example.apiroomdb.viewmodel.EmployeeDetailViewModel
import com.example.apiroomdb.viewmodel.EmployeeDetailViewModelFactory

class EmployeeDetailFragment : Fragment() {

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployeeDetailViewModel by viewModels {
        val dao = AppDatabase.getDatabase(requireContext()).employeeDao()
        val repo = EmployeeRepository(dao)
        val args = requireArguments()
        val employeeId = args.getInt("employeeId")
        EmployeeDetailViewModelFactory(repo, employeeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Observe employee details
        viewModel.employee.observe(viewLifecycleOwner) { employee ->
            if (employee != null) {
                binding.tvName.text = "Name: ${employee.name}"
                binding.tvMobile.text = "Mobile: ${employee.mobile}"
                binding.tvDesignation.text = "Designation: ${employee.designation}"
            } else {
                Toast.makeText(requireContext(), "Employee not found", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
