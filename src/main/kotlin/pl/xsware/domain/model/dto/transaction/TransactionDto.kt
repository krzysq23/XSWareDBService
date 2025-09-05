package pl.xsware.domain.model.dto.transaction

import pl.xsware.domain.model.entity.category.CategoryType
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class TransactionDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val categoryName: String?,
    val amount: BigDecimal,
    val date: LocalDate,
    val description: String?,
    val type: CategoryType?,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
