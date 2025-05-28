package pl.xsware.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.ResponseStatus
import pl.xsware.domain.model.dto.UserDto
import pl.xsware.domain.model.dto.UserRequest
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
    fun authenticate(@RequestBody data: UserRequest): ResponseEntity<UserDto> {
        log.info("START method: authenticate, email: = {}", data.email)
        val user = userService.getUserByEmail(data.email)?.toUserDto()
            ?: throw NoSuchElementException("Użytkownik o adresie e-mail ${data.email} nie istnieje")
        if(!authService.isValidEmailAndPassword(data.email, data.password)) {
            throw BadCredentialsException("Nieprawidłowy login lub hasło")
        }
        log.info("END method: authenticate, data: = {}", user)
        return ResponseEntity.ok(user)
    }

    @PostMapping("/exist")
    fun checkIfUserExist(@RequestBody data: UserRequest): ResponseEntity<Response> {
        log.info("START method: checkIfUserExist, data: = {}", data)
        val response = if( userService.checkIfExist(data.email) )
             Response(message = "Użytkownik istnieje")
            else Response(message = "Uźytkonik nie istnieje", status = ResponseStatus.ERROR);
        log.info("END method: checkIfUserExist, response: = {}", response)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/create")
    fun createUser(@RequestBody data: UserRequest): ResponseEntity<Response> {
        log.info("Method: createUser, data: = {}", data)
        userService.createUser(data)
        return ResponseEntity.ok(Response(message = "Utowrzono użytkownika"))
    }

}