package com.telegramplus.app.data.repository

import com.telegramplus.app.data.local.dao.UserDao
import com.telegramplus.app.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    
    suspend fun getUserById(userId: String): User? = userDao.getUserById(userId)
    
    fun getUserByIdFlow(userId: String): Flow<User?> = userDao.getUserByIdFlow(userId)
    
    suspend fun getUserByPhone(phoneNumber: String): User? = userDao.getUserByPhone(phoneNumber)
    
    suspend fun getUserByUsername(username: String): User? = userDao.getUserByUsername(username)
    
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    
    fun getOnlineUsers(): Flow<List<User>> = userDao.getOnlineUsers()
    
    fun searchUsers(query: String): Flow<List<User>> = userDao.searchUsers("%$query%")
    
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    
    suspend fun insertUsers(users: List<User>) = userDao.insertUsers(users)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    
    suspend fun updateUserStatus(userId: String, isOnline: Boolean, lastSeen: Long? = null) {
        userDao.updateUserStatus(userId, isOnline, lastSeen)
    }
}
