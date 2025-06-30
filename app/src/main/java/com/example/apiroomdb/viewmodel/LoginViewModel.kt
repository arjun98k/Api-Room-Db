package com.example.apiroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiroomdb.data.remote.LoginRequest

import com.example.apiroomdb.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository() // You may inject in real app
) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.loginUser(LoginRequest(email, password))
            _loginResult.postValue(result.isSuccessful)
        }
    }
}