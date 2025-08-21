package pl.xsware.domain.service

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import pl.xsware.api.util.MyCustomException
import pl.xsware.domain.model.dto.notification.NotificationDto
import pl.xsware.domain.respository.NotificationRepository
import pl.xsware.util.mapper.toDto
import pl.xsware.util.mapper.toEntity

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val entityManager: EntityManager
) {

    fun getAllNotification(userId: Long): List<NotificationDto>? {
        return notificationRepository.findByUserId(userId)
            .map { it.toDto() }
    }

    fun addNotification(notificationDto: NotificationDto) {
        notificationRepository.save(notificationDto.toEntity(entityManager))
    }

    fun removeNotification(notificationId: Long, userId: Long) {
        val deletedCount = notificationRepository.deleteByIdAndUserId(notificationId, userId)
        if (deletedCount == 0L) {
            throw MyCustomException("Nie znaleziono powiadomienia")
        }
    }

    fun editNotification(notificationDto: NotificationDto) {
        val existing = notificationRepository.findById(notificationDto.id!!)
            .orElseThrow { MyCustomException("Powiadomienie nie istnieje") }

        existing.message = notificationDto.message
        existing.type = notificationDto.type
        existing.isRead = notificationDto.isRead

        notificationRepository.save(existing)
    }

    fun changeAsRead(data: List<NotificationDto>) {
        // TODO:
    }
}