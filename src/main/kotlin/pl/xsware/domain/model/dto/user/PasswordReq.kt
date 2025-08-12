package pl.xsware.domain.model.dto.user

import jakarta.validation.constraints.NotBlank

data class PasswordReq(

    @field:NotBlank
    val login: String = "",
    @field:NotBlank
    val oldPassword: String = "",
    @field:NotBlank
    val password: String = ""
)
