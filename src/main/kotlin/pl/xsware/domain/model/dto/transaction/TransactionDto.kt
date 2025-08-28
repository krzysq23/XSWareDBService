package pl.xsware.domain.model.dto.transaction

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class TransactionDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val amount: BigDecimal,
    val date: LocalDate,
    val description: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
