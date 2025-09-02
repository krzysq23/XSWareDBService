package pl.xsware.domain.model.entity.budget

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pl.xsware.domain.model.entity.category.Category
import pl.xsware.domain.model.entity.user.User
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(
    name = "budget_limits",
    indexes = [
        Index(name = "idx_budget_limits_user_id", columnList = "user_id"),
        Index(name = "idx_budget_limits_category_id", columnList = "category_id")
    ]
)
data class BudgetLimit(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_budget_limits_users"))
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "fk_budget_limits_categories"))
    var category: Category? = null,

    @Column(name = "amount_limit", nullable = false, precision = 10, scale = 2)
    var amountLimit: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type", nullable = false, length = 16)
    var periodType: PeriodType,

    @Column(name = "start_date", nullable = false)
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate? = null,

    @Column(name = "note")
    var note: String?,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant? = null
)