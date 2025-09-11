package ait.shop.repository;

import ait.shop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.09.2025
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByTitle(String title);

}
