package pl.xsware.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.xsware.domain.model.dto.Response
import pl.xsware.domain.model.dto.notification.NotificationDto
import pl.xsware.domain.service.NotificationService

@RestController
@RequestMapping("/api/v1/notification")
class NotificationController(
    private val notificationService: NotificationService
) {

    @GetMapping("/all/{userId}")
    fun getAllNotification(@PathVariable userId: Long): ResponseEntity<List<NotificationDto>> {
        val list = notificationService.getAllNotification(userId);
        return  ResponseEntity.ok(list)
    }

    @PostMapping("/add")
    fun addNotification(@RequestBody data: NotificationDto): ResponseEntity<Response> {
        notificationService.addNotification(data)
        return ResponseEntity.ok(Response(message = "Dodano powiadomienie"))
    }

    @GetMapping("/remove")
    fun removeNotification(@RequestParam notificationId: Long,
                           @RequestParam userId: Long): ResponseEntity<Response> {
        notificationService.removeNotification(notificationId, userId)
        return ResponseEntity.ok(Response(message = "Usunięto powiadomienie"))
    }

    @PostMapping("/edit")
    fun editNotification(@RequestBody data: NotificationDto): ResponseEntity<Response> {
        notificationService.editNotification(data)
        return ResponseEntity.ok(Response(message = "Zmieniono powiadomienie"))
    }
}