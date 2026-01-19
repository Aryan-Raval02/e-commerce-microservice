package com.project.ecommerce.service.category;

import com.project.ecommerce.dto.response.DropDown;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.utility.ResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String categoryName) {
        Category category = new Category();

        category.setCategoryName(categoryName);
        category = categoryRepository.save(category);

        return category;
    }

    @Override
    public ResultDto<DropDown> getDropDown() {

        List<DropDown> list = new ArrayList<>();
        for(Category category : categoryRepository.findAll()){
            list.add(new DropDown(category.getCategoryId(), category.getCategoryName()));
        }

        ResultDto<DropDown> resultDto = new ResultDto<>();

        resultDto.setList(list);
        resultDto.setTotal(list.size());

        return resultDto;
    }
}
