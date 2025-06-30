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
import com.example.apiroomdb.entity.EmployeeEntity
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
        var isEditing = false
        var currentEmployee: EmployeeEntity? = null

        viewModel.employee.observe(viewLifecycleOwner) { employee ->
            if (employee != null) {
                currentEmployee = employee
                binding.tvName.text = "Name: ${employee.name}"
                binding.tvMobile.text = "Mobile: ${employee.mobile}"
                binding.tvDesignation.text = "Designation: ${employee.designation}"

                binding.etName.setText(employee.name)
                binding.etMobile.setText(employee.mobile)
                binding.etDesignation.setText(employee.designation)
            } else {
                Toast.makeText(requireContext(), "Employee not found", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        binding.btnEditSave.setOnClickListener {
            if (!isEditing) {
                // Switch to edit mode
                isEditing = true
                binding.btnEditSave.text = "Save"

                binding.tvName.visibility = View.GONE
                binding.tvMobile.visibility = View.GONE
                binding.tvDesignation.visibility = View.GONE

                binding.etName.visibility = View.VISIBLE
                binding.etMobile.visibility = View.VISIBLE
                binding.etDesignation.visibility = View.VISIBLE

            } else {
                // Save the updated data
                val updatedEmployee = currentEmployee?.copy(
                    name = binding.etName.text.toString(),
                    mobile = binding.etMobile.text.toString(),
                    designation = binding.etDesignation.text.toString()
                )

                if (updatedEmployee != null) {
                    viewModel.updateEmployee(updatedEmployee)
                    Toast.makeText(requireContext(), "Employee updated", Toast.LENGTH_SHORT).show()
                }

                isEditing = false
                binding.btnEditSave.text = "Edit"

                binding.tvName.text = "Name: ${binding.etName.text}"
                binding.tvMobile.text = "Mobile: ${binding.etMobile.text}"
                binding.tvDesignation.text = "Designation: ${binding.etDesignation.text}"

                binding.tvName.visibility = View.VISIBLE
                binding.tvMobile.visibility = View.VISIBLE
                binding.tvDesignation.visibility = View.VISIBLE

                binding.etName.visibility = View.GONE
                binding.etMobile.visibility = View.GONE
                binding.etDesignation.visibility = View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
