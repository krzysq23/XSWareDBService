package pl.xsware.domain.model.entity.notification

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import pl.xsware.domain.model.entity.user.User
import java.time.Instant

@Entity
@Table(
    name = "notifications",
    indexes = [
        Index(name = "idx_notifications_user_id", columnList = "user_id"),
        Index(name = "idx_notifications_is_read", columnList = "is_read")
    ]
)
data class Notification(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_notifications_users"))
    val user: User,

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    var message: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    var type: NotificationType,

    @Column(name = "is_read", nullable = false)
    var isRead: Boolean = false,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null
)