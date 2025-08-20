package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.model.dto.category.CategoryDto
import pl.xsware.domain.respository.CategoryRepository

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getAllCategories(): List<CategoryDto>? {
        return null
    }

    fun getCategoriesByUser(userId: Long): List<CategoryDto>? {
        return null
    }

    fun addCategory(data: CategoryDto) {

    }

    fun removeCategory(data: Long, userId: Long) {

    }

    fun editCategory(data: CategoryDto) {

    }
}