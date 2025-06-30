package com.example.apiroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.entity.EmployeeEntity
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    val allEmployees: LiveData<List<EmployeeEntity>> = repository.getAllEmployees()

    fun deleteEmployee(employee: EmployeeEntity) {
        viewModelScope.launch {
            repository.deleteEmployee(employee)
        }
    }
}
