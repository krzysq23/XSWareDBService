package pl.xsware.api

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.user.PasswordReq
import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.dto.user.UserLoginReq
import pl.xsware.domain.service.AuthService
import pl.xsware.domain.service.UserService
import pl.xsware.util.mapper.toUserDto

@RestController
@RequestMapping("/api/v1")
class AuthController(
    private val userService: UserService,
    private val authService: AuthService
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody data: UserLoginReq): ResponseEntity<UserDto> {
        val user = if (!data.login.isNullOrEmpty()) {
            val foundUser = userService.getUserByLogin(data.login)?.toUserDto()
                ?: throw MyCustomException("Nie odnaleziono użytkownika ${data.login}")
            if (!authService.isValidLoginAndPassword(data.login, data.password)) {
                throw MyCustomException("Nieprawidłowy login lub hasło")
            }
            foundUser
        } else if (!data.email.isNullOrEmpty()) {
            val foundUser = userService.getUserByEmail(data.email)?.toUserDto()
                ?: throw MyCustomException("Nie odnaleziono użytkownika ${data.email}")
            if (!authService.isValidEmailAndPassword(data.email, data.password)) {
                throw MyCustomException("Nieprawidłowy login lub hasło")
            }
            foundUser
        } else {
            throw MyCustomException("Brak loginu lub email do autoryzacji")
        }
        return ResponseEntity.ok(user)
    }

    @PostMapping("/validPassword")
    fun validPassword(@Valid  @RequestBody data: PasswordReq): ResponseEntity<Boolean> {
        val isValid = authService.validPassword(data);
        return ResponseEntity.ok(isValid)
    }

    @PostMapping("/changePassword")
    fun changePassword(@Valid @RequestBody data: PasswordReq): ResponseEntity<Response> {
        authService.changePassword(data);
        return ResponseEntity.ok(Response(message = "Zmieniono hasło"))
    }

}