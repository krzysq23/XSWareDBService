package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/budget")
class BudgetController {

    @GetMapping("/all/{login}")
    fun getAllUserBudget(@PathVariable login: String): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

}