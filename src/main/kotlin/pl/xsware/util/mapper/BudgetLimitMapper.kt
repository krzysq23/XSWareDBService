package pl.xsware.util.mapper

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

fun BudgetLimitDto.toEntity(user: User, category: Category) = BudgetLimit(
    id = this.id!!,
    user = user,
    category = category,
    amountLimit = this.amountLimit,
    periodType = this.periodType,
    startDate = this.startDate,
    endDate = this.endDate,
    createdAt = this.createdAt
)