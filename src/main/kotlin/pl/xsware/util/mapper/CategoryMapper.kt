package pl.xsware.util.mapper

import jakarta.persistence.EntityManager
import pl.xsware.domain.model.dto.category.CategoryDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.user.User

fun Category.toDto() = CategoryDto(
    id = this.id,
    userId = this.user.id,
    name = this.name,
    type = this.type,
    color = this.color,
    createdAt = this.createdAt!!
)


fun CategoryDto.toEntity(entityManager: EntityManager) = Category(
    id = this.id!!,
    user = entityManager.getReference(User::class.java, this.userId),
    name = this.name,
    type = this.type,
    color = this.color,
    createdAt = this.createdAt
)