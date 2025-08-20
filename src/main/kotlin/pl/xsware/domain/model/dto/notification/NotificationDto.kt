package pl.xsware.domain.model.dto.notification

import pl.xsware.domain.model.entity.notification.NotificationType
import java.time.LocalDateTime

data class NotificationDto(

    val id: Long?,
    val userId: Long,
    val message: String,
    val type: NotificationType,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
)
