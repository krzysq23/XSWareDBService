package pl.xsware.domain.respository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.xsware.domain.model.entity.user.Role
import pl.xsware.domain.model.entity.user.RoleName


@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: RoleName): Role?
    fun findByNameIn(names: Set<RoleName>): List<Role>
}