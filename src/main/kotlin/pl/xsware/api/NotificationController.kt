package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.service.NotificationService

@RestController
@RequestMapping("/api/v1/notification")
class NotificationController(
    private val notificationService: NotificationService
) {

    @GetMapping("/all/{userId}")
    fun getAllNotification(@PathVariable userId: String): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/add")
    fun addNotification(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/remove")
    fun removeNotification(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }

    @PostMapping("/edit")
    fun editNotification(): ResponseEntity<String> {
        return  ResponseEntity.ok("OK")
    }
}