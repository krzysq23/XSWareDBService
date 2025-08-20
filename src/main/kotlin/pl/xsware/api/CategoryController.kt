package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.category.CategoryDto
import pl.xsware.domain.service.CategoryService

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<List<CategoryDto>> {
        val list = categoryService.getAllCategories();
        return ResponseEntity.ok(list)
    }

    @GetMapping("/all/{userId}")
    fun getAllUserCategories(@PathVariable userId: Long): ResponseEntity<List<CategoryDto>> {
        val list = categoryService.getCategoriesByUser(userId);
        return ResponseEntity.ok(list)
    }

    @PostMapping("/add")
    fun addCategory(@RequestBody data: CategoryDto): ResponseEntity<Response> {
        categoryService.addCategory(data);
        return ResponseEntity.ok(Response(message = "Dodano kategorię"))
    }

    @GetMapping("/remove")
    fun removeCategory(@RequestParam categoryId: Long,
                       @RequestParam userId: Long): ResponseEntity<Response> {
        categoryService.removeCategory(categoryId, userId);
        return ResponseEntity.ok(Response(message = "Usunięto kategorię"))
    }

    @PostMapping("/edit")
    fun editCategory(@RequestBody data: CategoryDto): ResponseEntity<Response> {
        categoryService.editCategory(data);
        return ResponseEntity.ok(Response(message = "Zmieniono kategorię"))
    }
}