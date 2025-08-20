package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.model.dto.budget.BudgetLimitDto
import pl.xsware.domain.respository.BudgetRepository

@Service
class BudgetService(
    private val budgetRepository: BudgetRepository
) {

    fun getAllBudgetsForUser(userId: Long): List<BudgetLimitDto>? {
        return null
    }

    fun addBudget(data: BudgetLimitDto) {

    }

    fun removeBudget(budgetId: Long, userId: Long) {

    }

    fun editBudget(data: BudgetLimitDto) {

    }
}