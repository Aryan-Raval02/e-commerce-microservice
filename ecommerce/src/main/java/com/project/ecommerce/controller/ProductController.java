package com.project.ecommerce.controller;

import com.project.ecommerce.dto.request.ProductRequest;
import com.project.ecommerce.dto.response.ProductResponse;
import com.project.ecommerce.service.product.ProductService;
import com.project.ecommerce.utility.ResponseBuilder;
import com.project.ecommerce.utility.ResponseStructure;
import com.project.ecommerce.utility.ResultDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/v1/product")
    public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info(" :: [ProductController] :: HIT API for Add Product :: ");
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "Product Added Successfully !!", productResponse);
    }

    @GetMapping("/v1/product")
    public ResponseEntity<ResponseStructure<ResultDto<ProductResponse>>> findAll() {
        log.info(" :: [ProductController] :: HIT API for Find All Products :: ");
        ResultDto<ProductResponse> resultDto = productService.findALlProducts();
        return ResponseBuilder.success(HttpStatus.OK, "Fetched All Products Successfully !!", resultDto);
    }

    @GetMapping("/v1/product/byId")
    public ResponseEntity<ResponseStructure<ProductResponse>> findById(@RequestParam Long id){
        log.info(" :: [ProductController] :: HIT API for Find Single Product :: ");
        ProductResponse productResponse = productService.findById(id);
        return ResponseBuilder.success(HttpStatus.OK, "Fetched Product Successfully !!", productResponse);
    }

    @PutMapping("/v1/product")
    public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(@RequestBody ProductRequest productRequest){
        log.info(" :: [ProductController] :: HIT API for Update Product :: ");
        ProductResponse productResponse = productService.updateProduct(productRequest);
        return ResponseBuilder.success(HttpStatus.OK, "Update Product Successfully !!", productResponse);
    }
}
