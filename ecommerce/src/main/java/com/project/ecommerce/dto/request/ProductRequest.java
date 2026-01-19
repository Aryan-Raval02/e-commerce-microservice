package com.project.ecommerce.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private Long productId;
    private String name;
    private String sku;
    private String description;

    @Positive(message = "Price Not Be Negative !!")
    private BigDecimal price;

    @Positive(message = "Stock Quantity Not Be Negative !!")
    @Min(value = 10, message = "Stock Quantity Not Be Less Than 10 !!")
    @Max(value = 500, message = "Stock Quantity Not Be More Than 500 !!")
    private Integer stockQuantity;

    @Positive(message = "Low Stock Threshold Not Be Negative !!")
    @Min(value = 5, message = "Low Stock Threshold Not Be Less Than 5 !!")
    @Max(value = 20, message = "Low Stock Threshold Not Be More Than 20 !!")
    private Integer lowStockThreshold = 5;

    private List<Long> categories;
}
