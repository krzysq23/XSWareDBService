package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.budget.BudgetLimitDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.respository.BudgetRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity

@Service
class BudgetService(
    private val budgetRepository: BudgetRepository,
    private val entityManager: EntityManager
) {

    fun getAllBudgetsForUser(userId: Long): List<BudgetLimitDto>? {
        return budgetRepository.findByUserId(userId)
            .map { it.toDto() }
    }

    fun addBudget(budgetLimitDto: BudgetLimitDto) {
        budgetRepository.save(budgetLimitDto.toEntity(entityManager))
    }

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
        existing.category = entityManager.getReference(Category::class.java, budgetLimitDto.categoryId)

        budgetRepository.save(existing)
    }
}