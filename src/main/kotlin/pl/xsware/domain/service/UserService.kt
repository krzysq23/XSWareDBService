package pl.xsware.domain.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.user.UserRegisterReq
import pl.xsware.domain.model.entity.user.RoleName
import pl.xsware.domain.model.entity.user.User
import pl.xsware.domain.respository.RoleRepository
import pl.xsware.domain.respository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun createUser(userData: UserRegisterReq): User {

        if (userRepository.existsByEmail(userData.email)) {
            throw MyCustomException("Użytkownik o email ${userData.email} już istnieje")
        }

        if (userRepository.existsByLogin(userData.login)) {
            throw MyCustomException("Użytkownik o loginie ${userData.login} już istnieje")
        }

        val userRole = roleRepository.findByName(RoleName.USER)
            ?: throw MyCustomException("Nie można odnaleźć roli użytkownika 'USER'")

        val encodedPassword = passwordEncoder.encode(userData.password)

        val newUser = User(
            userName = userData.userName,
            login = userData.login,
            email = userData.email,
            password = encodedPassword,
            roles = setOf(userRole)
        )

        return userRepository.save(newUser)
    }

    fun authenticateUser(email: String, passwordEncoder: String): Boolean {
        return userRepository.existsByEmail(email);
    }

    fun checkIfExist(email: String): Boolean {
        return userRepository.existsByEmail(email);
    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun getUserByLogin(login: String): User? {
        return userRepository.findByLogin(login)
    }
}