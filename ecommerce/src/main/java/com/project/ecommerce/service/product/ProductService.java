package com.project.ecommerce.service.product;

import com.project.ecommerce.dto.request.ProductRequest;
import com.project.ecommerce.dto.response.ProductResponse;
import com.project.ecommerce.utility.ResultDto;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ResultDto<ProductResponse> findALlProducts();

    ProductResponse findById(Long id);

    ProductResponse updateProduct(ProductRequest productRequest);
}
