package com.watasolutions.week7_t7

import com.watasolutions.week7_t7.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun loadUser() : List<User>{
        return userDao.getAll()
    }

    suspend fun addUser(user : User){
        userDao.insert(user)
    }
}