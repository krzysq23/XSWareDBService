package pl.xsware.domain.model.entity.category

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import pl.xsware.domain.model.entity.user.User
import java.time.Instant

@Entity
@Table(
    name = "categories",
    indexes = [
        Index(name = "idx_categories_user_id", columnList = "user_id")
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_categories_user_name", columnNames = ["user_id", "name"])
    ]
)
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_categories_users"))
    val user: User,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 16)
    var type: CategoryType,

    @Column(name = "color", length = 10)
    var color: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null
)
