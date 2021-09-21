package com.marvim.configuration

import com.marvim.model.User
import com.marvim.repository.UserRepository
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class StartupService(private val userRepository: UserRepository) {

    private val log = LoggerFactory.getLogger(StartupService::class.java)

    @EventListener
    fun onStartupEvent(event: StartupEvent) {
        val user = userRepository.saveEncoder(
            User(
                191,
                "123456"
            )
        )

        log.info("Usuario adicionado: {}", user)
    }
}