package pl.xsware.api

import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.ResponseStatus
import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.dto.user.UserLoginReq
import pl.xsware.domain.model.dto.user.UserRegisterReq
import pl.xsware.domain.service.AuthService
import pl.xsware.domain.service.UserService
import pl.xsware.util.toUserDto

@RestController
@RequestMapping("/api/v1/user/")
class UserController(
    private val userService: UserService,
    private val authService: AuthService
) {

    val log: Logger = LogManager.getLogger(UserController::class.java)

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody data: UserLoginReq): ResponseEntity<UserDto> {
        log.info("START method: authenticate, login: = {}", data.login)
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
        log.info("END method: authenticate, data: = {}", user)
        return ResponseEntity.ok(user)
    }

    @PostMapping("/exist")
    fun checkIfUserExist(@RequestBody data: UserRegisterReq): ResponseEntity<Response> {
        log.info("START method: checkIfUserExist, data: = {}", data)
        val response = if( userService.checkIfExist(data.login) )
             Response(message = "Użytkownik istnieje")
            else Response(message = "Uźytkonik nie istnieje", status = ResponseStatus.ERROR);
        log.info("END method: checkIfUserExist, response: = {}", response)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/create")
    fun createUser(@Valid @RequestBody data: UserRegisterReq): ResponseEntity<Response> {
        log.info("Method: createUser, data: = {}", data)
        userService.createUser(data)
        return ResponseEntity.ok(Response(message = "Utowrzono użytkownika"))
    }

}