package ait.shop.repository;

import ait.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergey Bugaenko
 * {@code @date} 04.09.2025
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Ни одного метода внутри нет
}
