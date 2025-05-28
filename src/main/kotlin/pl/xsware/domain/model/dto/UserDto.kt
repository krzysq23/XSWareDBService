package pl.xsware.domain.model.dto

import pl.xsware.domain.model.entity.Role

data class UserDto(

    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val roles: Set<Role> = emptySet()
)