package ait.shop.service;

import ait.shop.model.dto.ProductDTO;
import ait.shop.model.entity.Product;
import ait.shop.repository.ProductRepository;
import ait.shop.service.interfaces.ProductService;
import ait.shop.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 02.09.2025
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ProductDTO saveProduct(ProductDTO productDto) {
        Product product = mapper.mapDtoToEntity(productDto);
//        product.setActive(true);
       return mapper.mapEntityToDto(repository.save(product));
    }

    @Override
    public ProductDTO getById(Long id) {

        Product product = repository.findById(id).orElse(null);

       if (product == null || !product.isActive()) return null;
       // true || ? -> true
       // true | NPE -> true
       // false || ? ->

        // false && ? -> false
        // false & NPE ->

       return mapper.mapEntityToDto(product);
    }

    @Override
    public List<ProductDTO> getAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                // Добавим строку маппинга
                .map(mapper::mapEntityToDto)
//                .map(product -> mapper.mapEntityToDto(product))
                .toList();
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public ProductDTO deleteByTitle(String title) {
        return null;
    }

    @Override
    public ProductDTO restoreProductById(Long id) {
        return null;
    }

    @Override
    public long getProductCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }
}
