package com.news.service.impl;
import com.news.converter.impl.CategoryConverter;
import com.news.dto.CategoryDTO;
import com.news.entity.CategoryEntity;
import com.news.repository.CategoryRepository;
import com.news.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public void save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity;
        if (categoryDTO.getId() != null) {
            CategoryEntity oldCategoryEntity = categoryRepository.findById(categoryDTO.getId()).orElse(null);
            categoryEntity = categoryConverter.toEntity(categoryDTO, oldCategoryEntity);
        } else {
            categoryDTO.setIsDeleted(false);
            categoryEntity = categoryConverter.toEntity(categoryDTO);
        }
        categoryEntity = categoryRepository.save(categoryEntity);
        categoryConverter.toDTO(categoryEntity);
    }

    @Override
    public void deleteAll(List<CategoryDTO> dtos) {
        List<Long> ids = new ArrayList<>();
        dtos.forEach(newDTO -> {
            ids.add(newDTO.getId());
        });
        for (long id:ids){
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<CategoryDTO> findAll(){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryEntity -> {
            categoryDTOS.add(categoryConverter.toDTO(categoryEntity));
        });
        return categoryDTOS;
    }
}
