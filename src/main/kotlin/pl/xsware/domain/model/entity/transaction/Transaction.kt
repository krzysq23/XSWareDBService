package pl.xsware.domain.model.entity.transaction

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.user.User
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(
    name = "transactions",
    indexes = [
        Index(name = "idx_transactions_user_id", columnList = "user_id"),
        Index(name = "idx_transactions_category_id", columnList = "category_id"),
        Index(name = "idx_transactions_date", columnList = "date")
    ]
)
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_transactions_users"))
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "fk_transactions_categories"))
    var category: Category? = null,

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    var amount: BigDecimal,

    @Column(name = "date", nullable = false)
    var date: Instant,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant? = null
)
