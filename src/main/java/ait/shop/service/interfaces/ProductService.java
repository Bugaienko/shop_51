package ait.shop.service.interfaces;

import ait.shop.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 02.09.2025
 */

public interface ProductService {

    Product saveProduct(Product product);

    Product getById(Long id);
    List<Product> getAllActiveProducts();

    Product update(Long id, Product product);

    Product deleteById(Long id);

    Product deleteByTitle(String title);

    Product restoreProductById(Long id);

    long getProductCount();

    BigDecimal getTotalPrice();

    BigDecimal getAveragePrice();


}
