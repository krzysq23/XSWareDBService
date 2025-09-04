package pl.xsware.domain.model.dto.transaction

import java.time.LocalDate

data class TransactionReq(

    val userId: Long,
    val dateRange: String?,
    val startDate: LocalDate?,
    val endDate: LocalDate?
)
