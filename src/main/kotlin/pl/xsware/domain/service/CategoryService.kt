package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.respository.CategoryRepository

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
}