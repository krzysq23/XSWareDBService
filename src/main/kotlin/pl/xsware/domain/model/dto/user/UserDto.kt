package pl.xsware.domain.model.dto.user

import pl.xsware.domain.model.entity.user.Role

data class UserDto(

    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val login: String = "",
    val email: String = "",
    val roles: Set<Role> = emptySet()
)