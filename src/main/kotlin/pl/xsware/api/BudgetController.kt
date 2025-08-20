package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.service.BudgetService

@RestController
@RequestMapping("/api/v1/budget")
class BudgetController(
    private val budgetService: BudgetService
) {

    @GetMapping("/all/{userId}")
    fun getAllUserBudget(@PathVariable userId: String): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/add")
    fun addBudget(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/remove")
    fun removeBudget(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/edit")
    fun editBudget(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }
}