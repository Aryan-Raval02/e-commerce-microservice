package com.project.ecommerce.service.category;

import com.project.ecommerce.dto.response.DropDown;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.utility.ResultDto;

public interface CategoryService {
    Category addCategory(String categoryName);

    ResultDto<DropDown> getDropDown();
}
