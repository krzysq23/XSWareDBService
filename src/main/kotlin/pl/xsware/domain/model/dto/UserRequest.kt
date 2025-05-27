package pl.xsware.domain.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class UserRequest(

    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    @JsonIgnore
    val password: String = ""
)
