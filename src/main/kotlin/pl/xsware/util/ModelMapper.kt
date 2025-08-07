package pl.xsware.util

import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.entity.user.User

class ModelMapper

fun User.toUserDto(): UserDto {
    return UserDto(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        login = this.login,
        email = this.email,
        roles = this.roles
    )
}