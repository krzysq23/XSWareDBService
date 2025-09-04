package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.transaction.TransactionDto
import pl.xsware.domain.model.dto.transaction.TransactionReq
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.respository.TransactionRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity
import java.time.LocalDate

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val entityManager: EntityManager
) {

    fun getTransactionsByDate(data: TransactionReq): List<TransactionDto>? {
        if(!data.dateRange.isNullOrEmpty())
            return when (data.dateRange.lowercase()) {
                "today" -> transactionRepository.findByUserIdAndDateBetween(data.userId, LocalDate.now(), LocalDate.now())
                    .map { it.toDto() }

                "month" -> transactionRepository.findByUserIdAndDateGreaterThanEqual(data.userId, LocalDate.now().minusMonths(1),)
                    .map { it.toDto() }

                "lastWeek" -> transactionRepository.findByUserIdAndDateGreaterThanEqual(data.userId, LocalDate.now().minusWeeks(1))
                    .map { it.toDto() }

                "lastQuarter" -> transactionRepository.findByUserIdAndDateGreaterThanEqual(data.userId, LocalDate.now().minusMonths(3))
                    .map { it.toDto() }

                "lastYear" -> transactionRepository.findByUserIdAndDateGreaterThanEqual(data.userId, LocalDate.now().minusYears(1))
                    .map { it.toDto() }

                else -> transactionRepository.findByUserId(data.userId)
                    .map { it.toDto() }
            }
        else if(data.startDate != null && data.endDate != null)
            return transactionRepository.findByUserIdAndDateBetween(data.userId, data.startDate, data.endDate)
                .map { it.toDto() }
        else
            return null;
    }

    fun addTransaction(transactionDto: TransactionDto) {
        transactionRepository.save(transactionDto.toEntity(entityManager))
    }

    @Transactional
    fun removeTransaction(transactionId: Long, userId: Long) {
        val deletedCount = transactionRepository.deleteByIdAndUserId(transactionId, userId)
        if (deletedCount == 0L) {
            throw MyCustomException("Nie znaleziono powiadomienia")
        }
    }

    fun editTransaction(transactionDto: TransactionDto) {
        val existing = transactionRepository.findById(transactionDto.id!!)
            .orElseThrow { MyCustomException("Powiadomienie nie istnieje") }

        existing.category = entityManager.getReference(Category::class.java, transactionDto.categoryId)
        existing.amount = transactionDto.amount
        existing.date = transactionDto.date
        existing.description = transactionDto.description

        transactionRepository.save(existing)
    }
}