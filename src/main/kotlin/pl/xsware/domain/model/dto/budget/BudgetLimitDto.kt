package pl.xsware.domain.model.dto.budget

import pl.xsware.domain.model.entity.budget.PeriodType
import java.time.Instant
import java.time.LocalDate

data class BudgetLimitDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val amountLimit: Double,
    val periodType: PeriodType,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val createdAt: Instant,
    val updatedAt: Instant?
)
