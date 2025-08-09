package pl.xsware.domain.model.dto.user

import jakarta.validation.constraints.NotBlank
import pl.xsware.domain.model.entity.user.Role
import pl.xsware.domain.model.entity.user.RoleName

data class UserReq(

    val id: Long,
    @field:NotBlank(message = "Pole userName nie może być puste")
    val userName: String = "",
    @field:NotBlank(message = "Pole login nie może być puste")
    val login: String = "",
    @field:NotBlank(message = "Pole email nie może być puste")
    val email: String = "",
    @field:NotBlank(message = "Pole password nie może być puste")
    val password: String = "",
    val role: Set<RoleName>
)
