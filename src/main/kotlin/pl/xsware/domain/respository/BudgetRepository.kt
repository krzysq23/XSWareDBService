package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.budget.BudgetLimit

@Repository
interface BudgetRepository : JpaRepository<BudgetLimit, Long> {
    fun findByUserId(userId: Long): List<BudgetLimit>
    fun findByUserIdAndCategoryId(userId: Long, categoryId: Long): BudgetLimit?
    fun deleteByIdAndUserId(id: Long, userId: Long): Long
    @Query(
        value = """
            SELECT b.id AS budgetId, b.user_id AS userId, b.amount_limit AS amountLimit,
                   b.category_id AS categoryId, b.period_type AS periodType, b.start_date AS startDate, 
                   b.start_date AS startDate, b.start_date AS startDate, b.end_date AS endDate, b.note AS note,
                   COALESCE(s.spent_amount, 0) AS amountSpent, COALESCE(s.remaining_amount, 0) AS amountRemaining
            FROM budget_limits b
            LEFT JOIN budget_summary s ON b.id = s.budget_id
            WHERE b.user_id = :userId
        """,
        nativeQuery = true
    )
    fun findBudgetWithSummary(@Param("userId") userId: Long): List<Map<String, Any>>
}