package com.project.ecommerce.controller;

import com.project.ecommerce.dto.response.DropDown;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.service.category.CategoryService;
import com.project.ecommerce.utility.ResponseBuilder;
import com.project.ecommerce.utility.ResponseStructure;
import com.project.ecommerce.utility.ResultDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/v1/category")
    public ResponseEntity<ResponseStructure<Category>> addCategory(@RequestParam String categoryName){
        Category category = categoryService.addCategory(categoryName);
        return ResponseBuilder.success(HttpStatus.CREATED, "Category Created !!", category);
    }

    @GetMapping("/v1/category/dropdown")
    public ResponseEntity<ResponseStructure<ResultDto<DropDown>>> categoryDropDown(){
        ResultDto<DropDown> dropdown = categoryService.getDropDown();
        return ResponseBuilder.success(HttpStatus.OK, "Category Drop Down !!", dropdown);
    }
}
