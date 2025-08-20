package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.model.dto.transaction.TransactionDto
import pl.xsware.domain.respository.TransactionRepository

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun getAllTransactions(userId: Long): List<TransactionDto>? {
        return null
    }

    fun addTransaction(data: TransactionDto) {

    }

    fun removeTransaction(transactionId: Long, userId: Long) {

    }

    fun editTransaction(data: TransactionDto) {

    }
}