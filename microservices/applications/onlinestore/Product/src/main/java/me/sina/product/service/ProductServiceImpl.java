package me.sina.product.service;

import me.sina.product.dto.ProductDTO;
import me.sina.product.model.Product;
import me.sina.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
//        CouponDTO couponDTO = paymentClient.findByCouponCode(productDTO.getCouponCode());
//        if (Objects.nonNull(couponDTO)) {
//            BigDecimal subtract = new BigDecimal("100").subtract(couponDTO.getDiscount());
//            product.setPrice(subtract.multiply(product.getPrice()).divide(new BigDecimal("100"), RoundingMode.UP));
//        }
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productDTOList.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }
}
