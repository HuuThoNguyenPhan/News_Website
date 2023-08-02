package com.news.service;

import com.news.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
    List<NewDTO> findAll(Pageable pageable);
    NewDTO findById(long id);
    NewDTO save(NewDTO newDTO);
    List<NewDTO> delete(long[] ids);
    int totalItem();
}
