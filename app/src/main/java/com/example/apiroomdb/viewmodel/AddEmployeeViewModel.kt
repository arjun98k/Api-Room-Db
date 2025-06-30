package com.example.apiroomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.entity.EmployeeEntity
import kotlinx.coroutines.launch

class AddEmployeeViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    fun insertEmployee(employee: EmployeeEntity) {
        viewModelScope.launch {
            repository.insertEmployee(employee)
        }
    }

    fun updateEmployee(employee: EmployeeEntity) {
        viewModelScope.launch {
            repository.updateEmployee(employee)
        }
    }
}
