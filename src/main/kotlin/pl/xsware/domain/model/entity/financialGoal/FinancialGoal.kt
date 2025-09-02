package pl.xsware.domain.model.entity.financialGoal

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
    name = "financial_goals",
    indexes = [
        Index(name = "idx_financial_goals_user_id", columnList = "user_id"),
        Index(name = "idx_financial_goals_category_id", columnList = "category_id")
    ]
)
data class FinancialGoal(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_financial_goals_users"))
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "fk_financial_goals_categories"))
    var category: Category? = null,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Column(name = "target_amount", nullable = false, precision = 10, scale = 2)
    var targetAmount: BigDecimal,

    @Column(name = "saved_amount", nullable = false, precision = 10, scale = 2)
    var savedAmount: BigDecimal,

    @Column(name = "deadline", nullable = false)
    var deadline: LocalDate,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant? = null
)
