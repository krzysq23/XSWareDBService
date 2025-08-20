package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.notification.Notification

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {
}