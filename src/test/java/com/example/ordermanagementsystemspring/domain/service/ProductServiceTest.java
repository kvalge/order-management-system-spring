package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.ProductMapper;
import com.example.ordermanagementsystemspring.domain.validation.ProductValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    ProductValidationService validationService;

    Product product = new Product();
    ProductDto productDto = new ProductDto();
    List<Product> products = new ArrayList<>();
    List<ProductDto> productDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        product.setName("Product Name");
        product.setUnitPrice(11.11F);

        products.add(product);

        productDto.setName("Product Name");
        productDto.setUnitPrice(11.11F);

        productDtos.add(productDto);
    }

    @Test
    void save() {
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto dto = productService.save(productDto);

        assertNotNull(dto);
        assertEquals(productDto.getName(), dto.getName());
    }

    @Test
    void findAll() {
        Mockito.doNothing().when(validationService).productsNotFound();
        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDtoList(products)).thenReturn(productDtos);

        List<ProductDto> productDtoList = productService.findAll();

        assertThat(productDtoList).isNotNull().isNotEmpty().hasSize(1);
        assertEquals("Product Name", productDtoList.get(0).getName());
    }

    @Test
    void findById() {
        Mockito.doNothing().when(validationService).productNotFound(productDto.getId());
        when(productRepository.findById(productDto.getId())).thenReturn(Optional.ofNullable(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto dto = productService.findById(productDto.getId());

        assertNotNull(dto);
        assertEquals("Product Name", dto.getName());
    }

    @Test
    void update() {
    }

    @Test
    void partialUpdate() {
    }

    @Test
    void delete() {
    }
}