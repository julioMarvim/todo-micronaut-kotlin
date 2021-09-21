package com.marvim.repository

import com.marvim.model.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder

@Repository
abstract class UserRepository(private val passwordEncoder: PasswordEncoder): JpaRepository<User, Long> {

    fun saveEncoder(entity: User): User{
        return save(User(entity.cpf, passwordEncoder.encode(entity.password)))
    }
}