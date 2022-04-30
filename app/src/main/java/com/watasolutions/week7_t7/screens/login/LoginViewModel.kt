package com.watasolutions.week7_t7.screens.login

import android.app.Application
import androidx.lifecycle.*
import com.watasolutions.week7_t7.AppDatabase
import com.watasolutions.week7_t7.UserRepository
import com.watasolutions.week7_t7.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var getAllData : List<User>
    private val repository : UserRepository
    private var _saveEventSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val saveEventSuccess: LiveData<Boolean>
        get() = _saveEventSuccess
    init{

        val userDAO = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDAO)
        viewModelScope.launch(Dispatchers.IO) {
            getAllData = repository.loadUser()
        }
    }
    private var _loadUserEvent: MutableLiveData<User> = MutableLiveData()
    val loadUserEvent: LiveData<User>
        get() = _loadUserEvent

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
        _saveEventSuccess.value = true
    }

    fun loadData() : List<User> {
        viewModelScope.launch(Dispatchers.IO) {
            getAllData = repository.loadUser()
        }
        return getAllData
    }
}