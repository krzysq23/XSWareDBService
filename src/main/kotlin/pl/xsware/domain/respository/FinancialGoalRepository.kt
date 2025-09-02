package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.financialGoal.FinancialGoal

@Repository
interface FinancialGoalRepository : JpaRepository<FinancialGoal, Long> {
    fun findByUserId(userId: Long): List<FinancialGoal>
    fun deleteByIdAndUserId(id: Long, userId: Long): Long
}