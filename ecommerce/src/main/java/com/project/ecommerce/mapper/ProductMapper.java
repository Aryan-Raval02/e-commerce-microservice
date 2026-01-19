package com.project.ecommerce.mapper;

import com.project.ecommerce.dto.request.ProductRequest;
import com.project.ecommerce.dto.response.ProductResponse;
import com.project.ecommerce.models.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categories", ignore = true)
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toListProductResponse(List<Product> products);

    @Mapping(target = "categories", ignore = true)
    void toMapTargetEntity(Product source, @MappingTarget Product target);
}
