package com.watasolutions.week7_t7

import androidx.room.*
import com.watasolutions.week7_t7.model.User

@Dao
interface UserDao {
    @Query ("SELECT * FROM users ORDER BY username ASC")
    fun getAll() : List<User>

    @Query ("SELECT * FROM users WHERE username LIKE :name")
    fun findByName(name: String) : User

    @Insert
    fun insertAll(vararg user: User) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: User): Long

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Query ("DELETE FROM users" )
    fun deleteAll()
}