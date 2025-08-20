package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.budget.BudgetLimit

@Repository
interface BudgetRepository : JpaRepository<BudgetLimit, Long> {
    fun findByUserId(userId: Long): List<BudgetLimit>
    fun findByUserIdAndCategoryId(userId: Long, categoryId: Long): BudgetLimit?
    fun deleteByIdAndUserId(id: Long, userId: Long): Long
}