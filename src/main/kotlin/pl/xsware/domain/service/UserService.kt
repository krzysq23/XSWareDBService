package pl.xsware.domain.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.dto.user.UserReq
import pl.xsware.domain.model.entity.user.RoleName
import pl.xsware.domain.model.entity.user.User
import pl.xsware.domain.respository.RoleRepository
import pl.xsware.domain.respository.UserRepository
import pl.xsware.util.toUser
import pl.xsware.util.toUserDto
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun getUserById(id: Long): UserDto {
        return userRepository.findById(id)
            .orElseThrow { MyCustomException("Użytkownik o ID $id nie istnieje") }
            .toUserDto();
    }

    fun getAllUsers(): List<UserDto>? {
        return userRepository.findAll()
            .map { it.toUserDto() }
    }

    fun checkIfExist(login: String): Boolean {
        return userRepository.existsByLogin(login);
    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun getUserByLogin(login: String): User? {
        return userRepository.findByLogin(login)
    }

    @Transactional
    fun createUser(userData: UserReq) {

        if (userRepository.existsByEmail(userData.email)) {
            throw MyCustomException("Użytkownik o email ${userData.email} już istnieje")
        }

        if (userRepository.existsByLogin(userData.login)) {
            throw MyCustomException("Użytkownik o loginie ${userData.login} już istnieje")
        }

        val userRole = roleRepository.findByName(RoleName.USER)
            ?: throw MyCustomException("Nie można odnaleźć roli użytkownika 'USER'")

        val encodedPassword = passwordEncoder.encode(userData.password)

        val newUser = userData.toUser(encodedPassword, mutableSetOf(userRole))

        userRepository.save(newUser)
    }

    fun editUser(userData: UserReq) {

        val editedUser = userRepository.findById(userData.id)
            .orElseThrow { MyCustomException("Użytkownik o nie widnieje w bazie danych") }

        if (userRepository.existsByEmailAndIdNot(userData.email, userData.id)) {
            throw MyCustomException("Email ${userData.email} już istnieje w bazie")
        }

        val userRole = roleRepository.findByNameIn(userData.role).toSet()
        val encodedPassword = passwordEncoder.encode(userData.password)

        editedUser.userName = userData.userName
        editedUser.email = userData.email
        editedUser.password = encodedPassword
        editedUser.roles
        editedUser.roles.addAll(userRole)

        userRepository.save(editedUser)
    }

    fun removeUserById(id: Long) {

    }
}