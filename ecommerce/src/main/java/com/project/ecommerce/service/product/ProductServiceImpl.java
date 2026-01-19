package com.project.ecommerce.service.product;

import com.project.ecommerce.dto.request.ProductRequest;
import com.project.ecommerce.dto.response.ProductResponse;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.mapper.ProductMapper;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.utility.ResultDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        List<Category> categories = categoryRepository.findAllById(productRequest.getCategories());

        if(categories.size() != productRequest.getCategories().size()){
            throw new ResourceNotFoundException("Some Inventory IDs Not Found !!");
        }
        product.setCategories(categories);
        Product saved = productRepository.save(product);

        log.info(" :: [ProductServiceImpl] :: Adding Product From DB :: According To Request");
        return toResponse(saved);
    }

    @Override
    public ResultDto<ProductResponse> findALlProducts() {
        List<ProductResponse> res = new ArrayList<>();

        for(Product product : productRepository.findAll()){
            res.add(toResponse(product));
        }

        ResultDto<ProductResponse> resultDto = new ResultDto<>();

        resultDto.setTotal(res.size());
        resultDto.setList(res);

        log.info(" :: [ProductServiceImpl] :: Getting All Products From DB :: Return To Client");
        return resultDto;
    }

    @Override
    public ProductResponse findById(Long id) {
        log.info(" :: [ProductServiceImpl] :: Finding Product In DB :: Return To Client");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found !!"));

        return toResponse(product);
    }


    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest) {
        log.info(" :: [ProductServiceImpl] :: Finding Product In DB :: For Updating");
        Product product = productRepository.findById(productRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found !!"));

        productMapper.toMapTargetEntity(productMapper.toProduct(productRequest), product);

        log.info(" :: [ProductServiceImpl] :: Product Updated :: Return To Client");
        Product updated = productRepository.save(product);
        return toResponse(updated);
    }

    private ProductResponse toResponse(Product product){
        ProductResponse productResponse = productMapper.toProductResponse(product);

        List<Long> categories = new ArrayList<>();
        for(Category category : product.getCategories()){
            categories.add(category.getCategoryId());
        }
        productResponse.setCategories(categories);

        return productResponse;
    }
}
