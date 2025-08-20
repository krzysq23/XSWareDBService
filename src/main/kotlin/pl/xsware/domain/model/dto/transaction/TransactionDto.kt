package pl.xsware.domain.model.dto.transaction

import java.math.BigDecimal
import java.time.Instant

data class TransactionDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val amount: BigDecimal,
    val date: Instant,
    val description: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
