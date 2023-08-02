package com.news.converter.impl;

import com.news.converter.ICategoryConverter;
import com.news.dto.CategoryDTO;
import com.news.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements ICategoryConverter {
    @Override
    public CategoryEntity toEntity(CategoryDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }
}
