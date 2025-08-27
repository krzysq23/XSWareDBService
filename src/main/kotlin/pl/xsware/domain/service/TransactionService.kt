package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.transaction.TransactionDto
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.respository.TransactionRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val entityManager: EntityManager
) {

    fun getAllTransactions(userId: Long): List<TransactionDto>? {
        return transactionRepository.findByUserId(userId)
            .map { it.toDto() }
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