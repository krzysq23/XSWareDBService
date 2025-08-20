package pl.xsware.domain.service

import org.springframework.stereotype.Service
import pl.xsware.domain.respository.NotificationRepository

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {
}