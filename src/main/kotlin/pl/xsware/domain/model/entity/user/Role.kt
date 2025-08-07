package pl.xsware.domain.model.entity.user

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val name: RoleName,

    @Column(name = "full_name", nullable = false)
    val fullName: String
)