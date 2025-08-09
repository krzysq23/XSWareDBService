package pl.xsware.domain.model.dto.user

import pl.xsware.domain.model.entity.user.RoleName

data class UserDto(

    val id: Long = 0,
    val userName: String = "",
    val login: String = "",
    val email: String = "",
    val roles: Set<RoleName> = emptySet()
)