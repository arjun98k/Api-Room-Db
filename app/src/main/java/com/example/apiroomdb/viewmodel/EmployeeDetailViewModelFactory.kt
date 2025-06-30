package com.example.apiroomdb.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiroomdb.data.repository.EmployeeRepository

class EmployeeDetailViewModelFactory(
    private val repository: EmployeeRepository,
    private val employeeId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeDetailViewModel(repository, employeeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
