package com.example.apiroomdb.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apiroomdb.data.local.AppDatabase
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.databinding.FragmentAddEmployeeBinding
import com.example.apiroomdb.entity.EmployeeEntity
import com.example.apiroomdb.viewmodel.AddEmployeeViewModel
import com.example.apiroomdb.viewmodel.AddEmployeeViewModelFactory

class AddEmployeeFragment : Fragment() {

    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddEmployeeViewModel by viewModels {
        val dao = AppDatabase.getDatabase(requireContext()).employeeDao()
        val repo = EmployeeRepository(dao)
        AddEmployeeViewModelFactory(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val mobile = binding.etMobile.text.toString().trim()
            val designation = binding.etDesignation.text.toString().trim()

            if (name.isEmpty() || mobile.isEmpty() || designation.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val employee = EmployeeEntity(
                name = name,
                mobile = mobile,
                designation = designation
            )

            viewModel.insertEmployee(employee)
            Toast.makeText(requireContext(), "Employee added", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
