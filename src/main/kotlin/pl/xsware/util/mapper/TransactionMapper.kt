package pl.xsware.util.mapper

import jakarta.persistence.EntityManager
import pl.xsware.domain.model.dto.transaction.TransactionDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.transaction.Transaction
import pl.xsware.domain.model.entity.user.User

fun Transaction.toDto() = TransactionDto(
    id = this.id,
    userId = this.user.id,
    categoryId = this.category!!.id,
    amount = this.amount,
    date = this.date,
    description = this.description,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun TransactionDto.toEntity(entityManager: EntityManager) = Transaction(
    id = this.id!!,
    user = entityManager.getReference(User::class.java, this.userId),
    category = entityManager.getReference(Category::class.java, this.categoryId),
    amount = this.amount,
    date = this.date,
    description = this.description,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)