package pl.xsware.domain.model.dto.user

data class UserRegisterReq(

    val firstName: String = "",
    val lastName: String = "",
    val login: String = "",
    val email: String = "",
    val password: String = ""
)
