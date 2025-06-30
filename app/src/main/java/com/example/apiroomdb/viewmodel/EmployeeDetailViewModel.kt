package com.example.apiroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.entity.EmployeeEntity
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

class EmployeeDetailViewModel(
    private val repository: EmployeeRepository,
    private val employeeId: Int
) : ViewModel() {

    private val _employee = MutableLiveData<EmployeeEntity?>()
    val employee: LiveData<EmployeeEntity?> get() = _employee

    init {
        fetchEmployee()
    }

    private fun fetchEmployee() {
        viewModelScope.launch {
            _employee.postValue(repository.getEmployeeById(employeeId))
        }
    }
}
