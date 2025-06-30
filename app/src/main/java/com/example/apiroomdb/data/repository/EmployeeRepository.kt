package com.example.apiroomdb.data.repository

import androidx.lifecycle.LiveData
import com.example.apiroomdb.data.local.dao.EmployeeDao
import com.example.apiroomdb.entity.EmployeeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class EmployeeRepository(
    private val employeeDao: EmployeeDao
) {

    // Insert new employee (save locally to Room)
    suspend fun insertEmployee(employee: EmployeeEntity) {
        withContext(Dispatchers.IO) {
            employeeDao.insertEmployee(employee)
        }
    }

    // Update existing employee
    suspend fun updateEmployee(employee: EmployeeEntity) {
        withContext(Dispatchers.IO) {
            employeeDao.updateEmployee(employee)
        }
    }

    // Delete employee
    suspend fun deleteEmployee(employee: EmployeeEntity) {
        withContext(Dispatchers.IO) {
            employeeDao.deleteEmployee(employee)
        }
    }

    // Get all employees (LiveData for observing in UI)
    fun getAllEmployees(): LiveData<List<EmployeeEntity>> {
        return employeeDao.getAllEmployees()
    }

    // Get a single employee by ID (if needed)
    suspend fun getEmployeeById(id: Int): EmployeeEntity? {
        return withContext(Dispatchers.IO) {
            employeeDao.getEmployeeById(id)
        }
    }
}