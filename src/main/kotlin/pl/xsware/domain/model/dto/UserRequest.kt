package pl.xsware.domain.model.dto

data class UserRequest(

    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)
