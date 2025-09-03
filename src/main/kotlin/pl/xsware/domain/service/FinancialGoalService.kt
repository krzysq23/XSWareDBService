package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.financialGoal.FinancialGoalDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.respository.FinancialGoalRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity

@Service
class FinancialGoalService(
    private val financialGoalRepository: FinancialGoalRepository,
    private val entityManager: EntityManager
) {

    fun getFinancialGoalByUser(userId: Long): List<FinancialGoalDto>? {
        return financialGoalRepository.findByUserId(userId)
            .map { it.toDto() };
    }

    fun addFinancialGoal(data: FinancialGoalDto) {
        financialGoalRepository.save(data.toEntity(entityManager))
    }

    @Transactional
    fun removeFinancialGoal(financialGoalId: Long, userId: Long) {
        val deletedCount = financialGoalRepository.deleteByIdAndUserId(financialGoalId, userId)
        if (deletedCount == 0L) {
            throw MyCustomException("Nie znaleziono celu finansowego")
        }
    }

    fun editFinancialGoal(data: FinancialGoalDto) {
        val existing = financialGoalRepository.findById(data.id!!)
            .orElseThrow { MyCustomException("Cel finansowy nie istnieje") }

        existing.category = entityManager.getReference(Category::class.java, data.categoryId)
        existing.name = data.name
        existing.targetAmount = data.targetAmount
        existing.savedAmount = data.savedAmount
        existing.deadline = data.deadline

        financialGoalRepository.save(existing)
    }
}