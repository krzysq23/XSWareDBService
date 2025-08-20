package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.service.TransactionService

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping("/all/{userId}")
    fun getAllUserTransactions(@PathVariable userId: String): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/add")
    fun addTransaction(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/remove")
    fun removeTransaction(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/edit")
    fun editTransaction(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }
}