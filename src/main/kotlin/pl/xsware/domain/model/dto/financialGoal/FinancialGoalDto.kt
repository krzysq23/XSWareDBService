package pl.xsware.domain.model.dto.financialGoal

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class FinancialGoalDto(

    val id: Long?,
    val userId: Long,
    val categoryId: Long,
    val name: String,
    val targetAmount: BigDecimal,
    val savedAmount: BigDecimal,
    val deadline: LocalDate,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
