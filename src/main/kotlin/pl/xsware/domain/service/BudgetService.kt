package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.budget.BudgetLimitDto
import pl.xsware.domain.model.dto.budget.BudgetReq
import pl.xsware.domain.model.entity.budget.PeriodType
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.respository.BudgetRepository
import pl.xsware.util.mapper.toEntity
import java.math.BigDecimal

@Service
class BudgetService(
    private val budgetRepository: BudgetRepository,
    private val entityManager: EntityManager
) {

    fun getBudgets(data: BudgetReq): List<BudgetLimitDto>? {
        return budgetRepository.findBudgetWithSummary(data.userId).map {
            BudgetLimitDto(
                id = (it["budgetId"] as Number).toLong(),
                userId = (it["budgetId"] as Number).toLong(),
                categoryId = (it["categoryId"] as Number).toLong(),
                amountLimit = it["amountLimit"] as BigDecimal,
                amountSpent = it["amountSpent"] as? BigDecimal ?: BigDecimal.ZERO,
                amountRemaining = it["amountRemaining"] as? BigDecimal ?: it["amountLimit"] as BigDecimal,
                periodType = PeriodType.valueOf(it["periodType"] as String),
                startDate = (it["startDate"] as java.sql.Date).toLocalDate(),
                endDate = (it["endDate"] as? java.sql.Date)?.toLocalDate(),
                note = it["note"] as String
            )
        }
    }

    fun addBudget(budgetLimitDto: BudgetLimitDto) {
        budgetRepository.save(budgetLimitDto.toEntity(entityManager))
    }

    @Transactional
    fun removeBudget(budgetId: Long, userId: Long) {
        val deletedCount = budgetRepository.deleteByIdAndUserId(budgetId, userId)
        if (deletedCount == 0L) {
            throw MyCustomException("Nie znaleziono limitu budżetowego")
        }
    }

    fun editBudget(budgetLimitDto: BudgetLimitDto) {
        val existing = budgetRepository.findById(budgetLimitDto.id!!)
            .orElseThrow { MyCustomException("Budżet nie istnieje") }

        existing.amountLimit = budgetLimitDto.amountLimit
        existing.periodType = budgetLimitDto.periodType
        existing.startDate = budgetLimitDto.startDate
        existing.endDate = budgetLimitDto.endDate
        existing.note = budgetLimitDto.note
        existing.category = entityManager.getReference(Category::class.java, budgetLimitDto.categoryId)

        budgetRepository.save(existing)
    }
}