package pl.xsware.domain.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.user.PasswordReq
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

    fun validPassword(data: PasswordReq): Boolean? {
        val user = userRepository.findByLogin(data.login)
            ?: throw MyCustomException("Użytkownik nie istnieje w bazie danych")

        return passwordEncoder.matches(data.password, user.password)
    }

    fun changePassword(data: PasswordReq) {
        val user = userRepository.findByLogin(data.login)
            ?: throw MyCustomException("Użytkownik nie istnieje w bazie danych")

        if(!passwordEncoder.matches(data.oldPassword, user.password)) {
            throw MyCustomException("Stare hasło jest nieporawidłowe")
        }
        val encodedPassword = passwordEncoder.encode(data.password)
        user.password = encodedPassword

        userRepository.save(user)
    }

}