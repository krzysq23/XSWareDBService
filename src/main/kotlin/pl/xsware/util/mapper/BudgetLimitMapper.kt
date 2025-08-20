package pl.xsware.util.mapper

import jakarta.persistence.EntityManager
import pl.xsware.domain.model.dto.budget.BudgetLimitDto
import pl.xsware.domain.model.entity.budget.BudgetLimit
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.user.User

fun BudgetLimit.toDto() = BudgetLimitDto(
    id = this.id,
    userId = this.user.id,
    categoryId = this.category!!.id,
    amountLimit = this.amountLimit,
    periodType = this.periodType,
    startDate = this.startDate,
    endDate = this.endDate,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun BudgetLimitDto.toEntity(entityManager: EntityManager) = BudgetLimit(
    id = this.id!!,
    user = entityManager.getReference(User::class.java, this.userId),
    category = entityManager.getReference(Category::class.java, this.categoryId),
    amountLimit = this.amountLimit,
    periodType = this.periodType,
    startDate = this.startDate,
    endDate = this.endDate,
    createdAt = this.createdAt
)