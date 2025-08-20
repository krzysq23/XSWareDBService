package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.category.CategoryDto
import pl.xsware.domain.respository.CategoryRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val entityManager: EntityManager
) {

    fun getAllCategories(): List<CategoryDto>? {
        return categoryRepository.findAll()
            .map { it.toDto() }
    }

    fun getCategoriesByUser(userId: Long): List<CategoryDto>? {
        return categoryRepository.findByUserId(userId)
            .map { it.toDto() }
    }

    fun addCategory(categoryDto: CategoryDto) {
        categoryRepository.save(categoryDto.toEntity(entityManager))
    }

    fun removeCategory(categoryId: Long, userId: Long) {
        val deletedCount = categoryRepository.deleteByIdAndUserId(categoryId, userId)
        if (deletedCount == 0L) {
            throw MyCustomException("Nie znaleziono kategorii")
        }
    }

    fun editCategory(categoryDto: CategoryDto) {
        val existing = categoryRepository.findById(categoryDto.id!!)
            .orElseThrow { MyCustomException("Budżet nie istnieje") }

        existing.name = categoryDto.name
        existing.type = categoryDto.type
        existing.color = categoryDto.color

        categoryRepository.save(existing)
    }
}