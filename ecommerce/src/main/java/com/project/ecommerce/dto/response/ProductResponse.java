package com.project.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "productId",
        "name",
        "sku",
        "description",
        "category",
        "price",
        "stockQuantity",
        "lowStockThreshold",
        "active",
        "categories",
        "createdAt",
        "updatedAt"
})
public class ProductResponse {

    private Long productId;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer lowStockThreshold;
    private Boolean active;
    private List<Long> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
