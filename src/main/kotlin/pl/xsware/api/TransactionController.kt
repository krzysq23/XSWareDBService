package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.transaction.TransactionDto
import pl.xsware.domain.model.dto.transaction.TransactionReq
import pl.xsware.domain.service.TransactionService

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping("/getByDate")
    fun getTransactionsByDate(@RequestBody data: TransactionReq): ResponseEntity<List<TransactionDto>> {
        val list = transactionService.getTransactionsByDate(data)
        return  ResponseEntity.ok(list)
    }

    @PostMapping("/add")
    fun addTransaction(@RequestBody data: TransactionDto): ResponseEntity<Response> {
        transactionService.addTransaction(data)
        return ResponseEntity.ok(Response(message = "Dodano transakcję"))
    }

    @GetMapping("/remove")
    fun removeTransaction(@RequestParam transactionId: Long,
                          @RequestParam userId: Long): ResponseEntity<Response> {
        transactionService.removeTransaction(transactionId, userId)
        return ResponseEntity.ok(Response(message = "Usunięto transakcję"))
    }

    @PostMapping("/edit")
    fun editTransaction(@RequestBody data: TransactionDto): ResponseEntity<Response> {
        transactionService.editTransaction(data)
        return ResponseEntity.ok(Response(message = "Zmieniono transakcję"))
    }
}