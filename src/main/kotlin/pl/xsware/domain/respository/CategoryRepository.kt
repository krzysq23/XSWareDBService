package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.category.Category

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}