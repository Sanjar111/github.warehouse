package com.warehouse.dao.level;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Level;

import java.util.List;

public interface LevelRepository {

    List<Level> findAll() throws EtResourceNotFoundException;

    Level findById(Integer level_id) throws EtResourceNotFoundException;

    Integer create(Integer level_number,Integer row_id, Integer[] subcategory_id_list, Integer status)throws EtBadRequestException;

    void update(Integer level_id, Integer level_number,Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

}

