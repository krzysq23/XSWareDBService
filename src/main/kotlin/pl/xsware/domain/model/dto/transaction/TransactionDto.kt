package pl.xsware.domain.model.dto.transaction

import java.time.Instant

data class TransactionDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val amount: Double,
    val date: Instant,
    val description: String?,
    val createdAt: Instant,
    val updatedAt: Instant?
)
