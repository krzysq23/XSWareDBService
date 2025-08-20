package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.transaction.Transactions

@Repository
interface TransactionRepository : JpaRepository<Transactions, Long> {
}