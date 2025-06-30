package com.example.apiroomdb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiroomdb.R
import com.example.apiroomdb.data.local.AppDatabase
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.databinding.FragmentMainBinding
import com.example.apiroomdb.viewmodel.MainViewModel
import com.example.apiroomdb.viewmodel.MainViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        val dao = AppDatabase.getDatabase(requireContext()).employeeDao()
        val repo = EmployeeRepository(dao)
        MainViewModelFactory(repo)
    }

    private lateinit var adapter: EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeEmployees()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addEmployeeFragment)
        }
    }

    private fun setupRecyclerView() {
        adapter = EmployeeAdapter(
            onItemClick = { employee ->
                val action = MainFragmentDirections.actionMainFragmentToEmployeeDetailFragment(employee.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { employee ->
                viewModel.deleteEmployee(employee)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeEmployees() {
        viewModel.allEmployees.observe(viewLifecycleOwner) { employees ->
            adapter.submitList(employees)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
