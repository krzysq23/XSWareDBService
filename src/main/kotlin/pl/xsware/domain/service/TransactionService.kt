package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.respository.TransactionRepository

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
}