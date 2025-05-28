package pl.xsware.domain.model.dto

import pl.xsware.domain.model.entity.RoleName

data class RoleDto(

    val id: Long = 0,
    val name: RoleName,
    val fullName: String
)
