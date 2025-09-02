package pl.xsware.util.mapper

import jakarta.persistence.EntityManager
import pl.xsware.domain.model.dto.financialGoal.FinancialGoalDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.financialGoal.FinancialGoal
import pl.xsware.domain.model.entity.user.User

fun FinancialGoal.toDto() = FinancialGoalDto(
    id = this.id,
    userId = this.user.id,
    categoryId = this.category!!.id,
    name = this.name,
    targetAmount = this.targetAmount,
    savedAmount = this.savedAmount,
    deadline = this.deadline,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun FinancialGoalDto.toEntity(entityManager: EntityManager) = FinancialGoal(
    id = this.id!!,
    user = entityManager.getReference(User::class.java, this.userId),
    category = entityManager.getReference(Category::class.java, this.categoryId),
    name = this.name,
    targetAmount = this.targetAmount,
    savedAmount = this.savedAmount,
    deadline = this.deadline,
    createdAt = this.createdAt
)