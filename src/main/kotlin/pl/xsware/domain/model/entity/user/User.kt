package pl.xsware.domain.model.entity.user

import jakarta.persistence.*
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    var userName: String,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    val login: String,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    var email: String,

    @field:NotBlank
    @Column(nullable = false)
    var password: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()

)