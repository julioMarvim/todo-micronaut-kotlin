package com.marvim.configuration.security

import com.marvim.repository.UserRepository
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.lang.Long.parseLong

@Singleton
class UserPasswordAuthenticationProvider(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val user = userRepository.findById(parseLong(authenticationRequest?.identity as String))

        if (user.isEmpty){
            return Flux.create { emitter: FluxSink<AuthenticationResponse> ->
                emitter.error(
                    AuthenticationResponse.exception(
                        AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
                    )
                )
            }
        }

        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            if (passwordEncoder.matches(authenticationRequest.secret as String, user.get().password)) {
                emitter.next(AuthenticationResponse.success(user.get().cpf.toString()))
                emitter.complete()
            } else {
                emitter.error(
                    AuthenticationResponse.exception(
                        AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
                    )
                )
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}