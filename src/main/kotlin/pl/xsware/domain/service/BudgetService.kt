package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.respository.BudgetRepository

@Service
class BudgetService(
    private val budgetRepository: BudgetRepository
) {
}