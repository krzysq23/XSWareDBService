package pl.xsware.domain.model.dto.budget

import pl.xsware.domain.model.entity.budget.PeriodType
import java.math.BigDecimal
import java.time.LocalDate

data class BudgetLimitDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val amountLimit: BigDecimal,
    val amountSpent: BigDecimal?,
    val amountRemaining: BigDecimal?,
    val periodType: PeriodType,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val note: String?,
)
