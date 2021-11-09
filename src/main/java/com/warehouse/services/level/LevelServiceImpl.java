package com.warehouse.services.level;


import com.warehouse.dao.level.LevelRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Level;
import com.warehouse.services.level.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {

    @Autowired
    LevelRepository levelRepository;

    @Override
    public List<Level> fetchAllCategories() {
        return levelRepository.findAll();
    }

    @Override
    public Level fetchCategoryById(Integer level_id) throws EtBadRequestException {
        return levelRepository.findById(level_id);
    }

    @Override
    public Level addCategory(Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        int level_id = levelRepository.create(level_number,row_id,subcategory_id_list,status);
        return levelRepository.findById(level_id);
    }

    @Override
    public void updateCategory(Integer level_id, Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        levelRepository.update(level_id,level_number,row_id,subcategory_id_list,status);
    }

    @Override
    public void removeCustomerById(Integer level_id) throws EtResourceNotFoundException {
        this.fetchCategoryById(level_id);
    }
}
