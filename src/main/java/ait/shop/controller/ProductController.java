package ait.shop.controller;

import ait.shop.model.entity.Product;
import ait.shop.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 02.09.2025
 */

// http://localhost:8080/api/products

@RestController
@RequestMapping("/products")
public class ProductController {

    public final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // POST /products
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    // GET /products/45
    // GET /products/3
    // GET /products/77
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        // Todo обращаемся к сервису и запрашиваем продукт по id
        return null;
    }

    //  GET /products
    @GetMapping
    public List<Product> getAll() {
        // Todo к сервису и запрашиваем все продукты
        return List.of();
    }

    // Update: PUT -> /products/id и в теле поля, которые мы хотим поменять
    @PutMapping("/{id}")
    public Product update (@PathVariable Long id, @RequestBody Product product) {
        // Todo Обращаемся к сервису для обновления продукта
        return new Product();
    }

    // Delete: DELETE -> products/id
    @DeleteMapping("/{productId}")
    public Product remove(@PathVariable("productId") Long id) {
        // Todo к сервису для удаления продукта
        return null;
    }

}


// POST /products/add - не правильно
// POST /products - правильно

// GET /products/getById/2 - не правильно
// GET /products/2 - правильно

// PUT /products/update/2 - не правильно
// PUT /products/2 - правильно

// DELETE /products/2