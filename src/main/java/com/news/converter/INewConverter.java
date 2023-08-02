package com.news.converter;

import com.news.dto.NewDTO;
import com.news.entity.NewEntity;

public interface INewConverter {
    NewEntity toEntity(NewDTO dto);
    NewEntity toEntity(NewDTO dto, NewEntity entity);
    NewDTO toDTO(NewEntity entity);
}
