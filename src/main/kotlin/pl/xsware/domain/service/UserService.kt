package pl.xsware.domain.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.xsware.domain.model.dto.UserRequest
import pl.xsware.domain.model.entity.User
import pl.xsware.domain.respository.RoleRepository
import pl.xsware.domain.respository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun createUser(userData: UserRequest): User {
        if (userRepository.existsByEmail(userData.email)) {
            throw IllegalArgumentException("User with email $userData.email already exists")
        }

        val userRole = roleRepository.findByName("USER")
            ?: throw IllegalStateException("Default role 'USER' not found")

        val encodedPassword = passwordEncoder.encode(userData.password)

        val newUser = User(
            firstName = userData.firstName,
            lastName = userData.lastName,
            email = userData.email,
            password = encodedPassword,
            roles = setOf(userRole)
        )

        return userRepository.save(newUser)
    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }
}