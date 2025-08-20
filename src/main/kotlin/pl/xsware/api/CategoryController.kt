package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.service.CategoryService

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @GetMapping("/all/{userId}")
    fun getAllUserCategories(@PathVariable userId: String): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/add")
    fun addCategory(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/remove")
    fun removeCategory(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/edit")
    fun editCategory(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }
}