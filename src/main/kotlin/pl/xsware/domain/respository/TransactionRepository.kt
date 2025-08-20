package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.transaction.Transaction
import java.time.LocalDate

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByUserId(userId: Long): List<Transaction>
    fun findByUserIdAndDateBetween(userId: Long, start: LocalDate, end: LocalDate): List<Transaction>
    fun deleteByIdAndUserId(id: Long, userId: Long): Long
}