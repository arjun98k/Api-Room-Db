package com.example.apiroomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiroomdb.data.repository.EmployeeRepository
import com.example.apiroomdb.entity.EmployeeEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    fun saveUserInfo(name: String, mobile: String, email: String) {
        viewModelScope.launch {
            // 🕒 Simulate network delay like a mock API
            delay(1000)

            // ✅ Save as an EmployeeEntity (or you could create separate entity if needed)
            val employee = EmployeeEntity(
                name = name,
                mobile = mobile,
                designation = email  // You can store email as designation placeholder, or create a separate user entity
            )

            repository.insertEmployee(employee)

            // You can post a LiveData event here for success if you want to notify the UI
        }
    }
}
