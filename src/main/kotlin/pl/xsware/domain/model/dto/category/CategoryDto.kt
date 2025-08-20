package pl.xsware.domain.model.dto.category

import pl.xsware.domain.model.entity.category.CategoryType
import java.time.Instant

data class CategoryDto(

    val id: Long?,
    val userId: Long,
    val name: String,
    val type: CategoryType,
    val color: String?,
    val createdAt: Instant,
    val updatedAt: Instant?
)
