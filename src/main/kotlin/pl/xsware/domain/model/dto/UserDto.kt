package pl.xsware.domain.model.dto

data class UserDto(

    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val roles: List<String> = emptyList()
)