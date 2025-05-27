package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.Role


@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}