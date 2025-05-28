package pl.xsware.util

import pl.xsware.domain.model.dto.UserDto
import pl.xsware.domain.model.entity.User

class ModelMapper

fun User.toUserDto(): UserDto {
    return UserDto(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        roles = this.roles
    )
}