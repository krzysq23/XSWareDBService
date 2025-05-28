package pl.xsware.domain.model.dto

data class Response(

    val message: String,
    val status: ResponseStatus = ResponseStatus.SUCCESS
)
