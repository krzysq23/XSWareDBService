package pl.xsware.util.mapper

import pl.xsware.domain.model.dto.notification.NotificationDto
import pl.xsware.domain.model.entity.notification.Notification
import pl.xsware.domain.model.entity.user.User

fun Notification.toDto() = NotificationDto(
    id = this.id,
    userId = this.user.id,
    message = this.message,
    type = this.type,
    isRead = this.isRead,
    createdAt = this.createdAt
)

fun NotificationDto.toEntity(user: User) = Notification(
    id = this.id!!,
    user = user,
    message = this.message,
    type = this.type,
    isRead = this.isRead,
    createdAt = this.createdAt
)