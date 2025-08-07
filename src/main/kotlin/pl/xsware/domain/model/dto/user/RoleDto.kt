package pl.xsware.domain.model.dto.user

import pl.xsware.domain.model.entity.user.RoleName

data class RoleDto(

    val id: Long = 0,
    val name: RoleName,
    val fullName: String
)
