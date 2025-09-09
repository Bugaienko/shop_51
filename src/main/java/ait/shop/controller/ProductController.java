package ait.shop.controller;

import ait.shop.model.dto.ProductDTO;
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
    @Operation(summary = "Create productDTO", description = "Add new productDTO.", tags = { "Product" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class)) }) })
    @PostMapping
    public ProductDTO saveProduct(@Parameter(description = "Create productDTO object") @RequestBody ProductDTO productDTO) {
        return service.saveProduct(productDTO);
    }

    // GET /products/45
    // GET /products/3
    // GET /products/77
    @Operation(summary = "Get product by id", description = "Get product by id", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ip supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ProductDTO getById(
            @Parameter(description = "The id that needs to be fetched.", required = true)
            @PathVariable Long id) {
        return service.getById(id);
    }

    //  GET /products
    @GetMapping
    public List<ProductDTO> getAll() {
        return service.getAllActiveProducts();
    }

    // Update: PUT -> /products/id и в теле поля, которые мы хотим поменять
    @PutMapping("/{id}")
    public ProductDTO update (@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return service.update(id, productDTO);
    }

    // Delete: DELETE -> products/id
    @DeleteMapping("/{productId}")
    public ProductDTO remove(@PathVariable("productId") Long id) {
        return service.deleteById(id);
    }

    // DELETE -> /products/by-title?title=Banana
    // DELETE -> /products/by-title/Banana - если бы использовали переменную пути
    @DeleteMapping("/by-title")
    public ProductDTO removeByTitle(@RequestParam String title) {
        return service.deleteByTitle(title);
    }

    @PutMapping("/restore/{id}")
    public ProductDTO restoreById(@PathVariable Long id) {
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