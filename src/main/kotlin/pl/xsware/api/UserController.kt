package pl.xsware.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.UserDto
import pl.xsware.domain.model.dto.UserRequest
import pl.xsware.domain.service.UserService
import pl.xsware.util.toUserDto

@RestController
@RequestMapping("/api/v1/user/")
class UserController(private val userService: UserService) {

    val log: Logger = LogManager.getLogger(UserController::class.java)

    @PostMapping("/getByEmail")
    fun getUser(@RequestBody data: UserRequest): ResponseEntity<UserDto> {
        log.info("Request data = {}", data)
        val user = userService.getUserByEmail(data.email)
            ?: throw NoSuchElementException("Użytkownik o adresie e-mail ${data.email} nie istnieje")
        return ResponseEntity.ok(user.toUserDto())
    }

    @PostMapping("/create")
    fun setUser(@RequestBody user: UserRequest): ResponseEntity<Response> {
        return ResponseEntity.ok(Response(message = "OKI"))
    }

}