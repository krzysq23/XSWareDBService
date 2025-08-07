package pl.xsware.domain.model.dto.user

data class UserLoginReq(

    val login: String? = "",
    val email: String? = "",
    val password: String = ""
)
