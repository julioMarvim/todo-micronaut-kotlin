package com.marvim.service

import com.marvim.model.User
import com.marvim.repository.UserRepository
import jakarta.inject.Singleton

@Singleton
class UserService(private  val userRepository: UserRepository) {
    fun addUser(user: User): User{
        return userRepository.saveEncoder(user);
    }
}