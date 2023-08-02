package com.news.service;

import com.news.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    void save(CategoryDTO categoryDTO);
    void deleteAll(List<CategoryDTO> dtos);
}
