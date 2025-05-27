package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}