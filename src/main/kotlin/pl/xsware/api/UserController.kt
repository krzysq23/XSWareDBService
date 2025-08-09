package pl.xsware.api

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.ResponseStatus
import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.dto.user.UserReq
import pl.xsware.domain.service.UserService

@RestController
@RequestMapping("/api/v1")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val user = userService.getUserById(id);
        return  ResponseEntity.ok(user)
    }

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        val users = userService.getAllUsers();
        return  ResponseEntity.ok(users)
    }

    @GetMapping("/user/exist/{login}")
    fun checkIfUserExist(@PathVariable login: String): ResponseEntity<Response> {
        return if (userService.checkIfExist(login)) {
            val response = Response(message = "Użytkownik istnieje", status = ResponseStatus.SUCCESS)
            ResponseEntity.ok(response)
        } else {
            val response = Response(message = "Użytkownik nie istnieje", status = ResponseStatus.ERROR)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
        }
    }

    @PostMapping("/user/create")
    fun createUser(@Valid @RequestBody data: UserReq): ResponseEntity<Response> {
        userService.createUser(data)
        return ResponseEntity.ok(Response(message = "Utowrzono użytkownika"))
    }

    @PostMapping("/user/edit")
    fun editUser(@Valid @RequestBody data: UserReq): ResponseEntity<Response> {
        userService.editUser(data)
        return ResponseEntity.ok(Response(message = "Zaktualizowano użytkownika"))
    }

    @GetMapping("/user/remove/{id}")
    fun removeUserById(@PathVariable id: Long): ResponseEntity<Response> {
        userService.removeUserById(id)
        return ResponseEntity.ok(Response(message = "Usunięto użytkownika"))
    }

}