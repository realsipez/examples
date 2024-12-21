package me.sina.product.service;

import me.sina.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
