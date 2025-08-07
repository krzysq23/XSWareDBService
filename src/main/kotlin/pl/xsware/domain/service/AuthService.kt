package pl.xsware.domain.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.xsware.domain.respository.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun isValidLoginAndPassword(login: String, rawPassword: String): Boolean {
        val user = userRepository.findByLogin(login) ?: return false
        return passwordEncoder.matches(rawPassword, user.password)
    }

    fun isValidEmailAndPassword(email: String, rawPassword: String): Boolean {
        val user = userRepository.findByEmail(email) ?: return false
        return passwordEncoder.matches(rawPassword, user.password)
    }

}