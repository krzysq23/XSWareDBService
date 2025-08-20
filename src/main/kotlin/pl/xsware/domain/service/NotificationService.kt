package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.model.dto.notification.NotificationDto
import pl.xsware.domain.respository.NotificationRepository

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {

    fun getAllNotification(userId: Long): List<NotificationDto>? {
        return null
    }

    fun addNotification(data: NotificationDto) {

    }

    fun removeNotification(notificationId: Long, userId: Long) {

    }

    fun editNotification(data: NotificationDto) {

    }
}