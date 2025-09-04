package ait.shop.controller;

import ait.shop.model.entity.Product;
import ait.shop.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 02.09.2025
 */

// http://localhost:8080/api/products

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Controller for operations with products")
public class ProductController {

    public final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // POST /products
    @Operation(summary = "Create product", description = "Add new product.", tags = { "Product" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = Product.class)) }) })
    @PostMapping
    public Product saveProduct(@Parameter(description = "Create product object") @RequestBody Product product) {
        return service.saveProduct(product);
    }

    // GET /products/45
    // GET /products/3
    // GET /products/77
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //  GET /products
    @GetMapping
    public List<Product> getAll() {
        return service.getAllActiveProducts();
    }

    // Update: PUT -> /products/id и в теле поля, которые мы хотим поменять
    @PutMapping("/{id}")
    public Product update (@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    // Delete: DELETE -> products/id
    @DeleteMapping("/{productId}")
    public Product remove(@PathVariable("productId") Long id) {
        return service.deleteById(id);
    }

    // DELETE -> /products/by-title?title=Banana
    // DELETE -> /products/by-title/Banana - если бы использовали переменную пути
    @DeleteMapping("/by-title")
    public Product removeByTitle(@RequestParam String title) {
        return service.deleteByTitle(title);
    }

    @PutMapping("/restore/{id}")
    public Product restoreById(@PathVariable Long id) {
        return service.restoreProductById(id);
    }

    @GetMapping("/count")
    public long getProductCount() {
        return service.getProductCount();
    }

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
        return service.getTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return service.getAveragePrice();
    }


}


// POST /products/add - не правильно
// POST /products - правильно

// GET /products/getById/2 - не правильно
// GET /products/2 - правильно

// PUT /products/update/2 - не правильно
// PUT /products/2 - правильно

// DELETE /products/2