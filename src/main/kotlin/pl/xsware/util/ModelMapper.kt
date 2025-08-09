package pl.xsware.util

import pl.xsware.domain.model.dto.user.UserDto
import pl.xsware.domain.model.dto.user.UserReq
import pl.xsware.domain.model.entity.user.Role
import pl.xsware.domain.model.entity.user.User

class ModelMapper

fun User.toUserDto(): UserDto {
    return UserDto(
        id = this.id,
        userName = this.userName,
        login = this.login,
        email = this.email,
        roles = this.roles.map { it.name }.toSet()
    )
}

fun UserReq.toUser(encodedPassword: String, roles: MutableSet<Role>): User {
    return User(
        id = this.id,
        userName = this.userName,
        login = this.login,
        email = this.email,
        password = encodedPassword,
        roles = roles
    )
}