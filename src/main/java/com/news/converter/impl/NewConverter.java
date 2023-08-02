package com.news.converter.impl;

import com.news.converter.INewConverter;
import com.news.dto.NewDTO;
import com.news.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class NewConverter implements INewConverter {

    @Override
    public NewEntity toEntity(NewDTO dto) {
        NewEntity entity = new NewEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getThumbnail());
        entity.setShortDescription(dto.getShortDescription());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public NewDTO toDTO(NewEntity entity) {
        NewDTO dto = new NewDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setThumbnail(entity.getThumbnail());
        dto.setShortDescription(entity.getShortDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setCategoryCode(entity.getCategory().getCode());
        return dto;
    }

    @Override
    public NewEntity toEntity(NewDTO dto, NewEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getThumbnail());
        entity.setShortDescription(dto.getShortDescription());
        return entity;
    }
}
