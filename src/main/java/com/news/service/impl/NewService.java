package com.news.service.impl;

import com.news.converter.impl.NewConverter;
import com.news.dto.NewDTO;
import com.news.entity.CategoryEntity;
import com.news.entity.NewEntity;
import com.news.repository.CategoryRepository;
import com.news.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.news.service.INewService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> newDTOs = new ArrayList<>();
        newRepository.findAll(pageable).forEach(newEntity -> {
            if (!newEntity.getIsDeleted())
                newDTOs.add(newConverter.toDTO(newEntity));
        });
        return newDTOs;
    }

    @Override
    public NewDTO findById(long id) {
        NewEntity newEntity = newRepository.findById(id).orElse(null);
        NewDTO newDTO = new NewDTO();
        if(newEntity != null){
            newDTO = newConverter.toDTO(newEntity);
        }
        return newDTO;
    }

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity;
        if (newDTO.getId() != null) {
            NewEntity oldNewEntity = newRepository.findById(newDTO.getId()).orElse(null);
            newEntity = newConverter.toEntity(newDTO, oldNewEntity);
        } else {
            newDTO.setIsDeleted(false);
            newEntity = newConverter.toEntity(newDTO);
        }
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategory(categoryEntity);
        newEntity = newRepository.save(newEntity);
        if(newDTO.getCreatedDate() != null){
            newEntity.setCreatedDate(newDTO.getCreatedDate());
        }
        return newConverter.toDTO(newEntity);
    }

    @Override
    public List<NewDTO> delete(long[] ids) {
        List<NewDTO> newDTOs = new ArrayList<>();
        for(long id:ids){
            NewEntity newEntity;
            NewEntity oldNewEntity = newRepository.findById(id).orElse(null);
            oldNewEntity.setIsDeleted(true);
            newEntity = newRepository.save(oldNewEntity);
            newDTOs.add(newConverter.toDTO(newEntity));
        }
        return newDTOs;
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }
}
