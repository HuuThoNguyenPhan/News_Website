package com.news.converter;

import com.news.dto.CategoryDTO;
import com.news.entity.CategoryEntity;


public interface ICategoryConverter {
    CategoryEntity toEntity(CategoryDTO dto);
    CategoryEntity  toEntity(CategoryDTO dto, CategoryEntity entity);
    CategoryDTO toDTO(CategoryEntity entity);
}
