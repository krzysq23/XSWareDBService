package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.budget.BudgetLimitDto
import pl.xsware.domain.service.BudgetService

@RestController
@RequestMapping("/api/v1/budget")
class BudgetController(
    private val budgetService: BudgetService
) {

    @GetMapping("/all/{userId}")
    fun getAllUserBudget(@PathVariable userId: Long): ResponseEntity<List<BudgetLimitDto>> {
        val listBL = budgetService.getAllBudgetsForUser(userId);
        return  ResponseEntity.ok(listBL)
    }

    @PostMapping("/add")
    fun addBudget(@RequestBody data: BudgetLimitDto): ResponseEntity<Response> {
        budgetService.addBudget(data);
        return  ResponseEntity.ok(Response(message = "Dodano budżet"))
    }

    @GetMapping("/remove")
    fun removeBudget(@RequestParam budgetId: Long,
                     @RequestParam userId: Long): ResponseEntity<Response> {
        budgetService.removeBudget(budgetId, userId);
        return  ResponseEntity.ok(Response(message = "Usunięto budżet"))
    }

    @PostMapping("/edit")
    fun editBudget(@RequestBody data: BudgetLimitDto): ResponseEntity<Response> {
        budgetService.editBudget(data);
        return  ResponseEntity.ok(Response(message = "Zmodyfikowano budżet"))
    }
}