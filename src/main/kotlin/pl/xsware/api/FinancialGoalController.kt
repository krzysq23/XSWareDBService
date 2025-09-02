package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.financialGoal.FinancialGoalDto
import pl.xsware.domain.service.FinancialGoalService

@RestController
@RequestMapping("/api/v1/financialGoal")
class FinancialGoalController(
    private val financialGoalService: FinancialGoalService
){

    @GetMapping("/all/{userId}")
    fun getFinancialGoalByUser(@PathVariable userId: Long): ResponseEntity<List<FinancialGoalDto>> {
        val list = financialGoalService.getFinancialGoalByUser(userId);
        return ResponseEntity.ok(list)
    }

    @PostMapping("/add")
    fun addFinancialGoal(@RequestBody data: FinancialGoalDto): ResponseEntity<Response> {
        financialGoalService.addFinancialGoal(data)
        return ResponseEntity.ok(Response(message = "Dodano cel finansowy"))
    }

    @GetMapping("/remove")
    fun removeFinancialGoal(@RequestParam financialGoalId: Long,
                          @RequestParam userId: Long): ResponseEntity<Response> {
        financialGoalService.removeFinancialGoal(financialGoalId, userId)
        return ResponseEntity.ok(Response(message = "Usunięto cel finansowy"))
    }

    @PostMapping("/edit")
    fun editFinancialGoal(@RequestBody data: FinancialGoalDto): ResponseEntity<Response> {
        financialGoalService.editFinancialGoal(data)
        return ResponseEntity.ok(Response(message = "Zmieniono cel finansowy"))
    }
}