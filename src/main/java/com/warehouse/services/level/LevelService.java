package com.warehouse.services.level;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Level;

import java.util.List;

public interface LevelService {

    List<Level> fetchAllCategories();

    Level fetchCategoryById(Integer level_id) throws EtBadRequestException;

    Level addCategory(Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

    void updateCategory(Integer level_id, Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

    void removeCustomerById(Integer level_id) throws EtResourceNotFoundException;
}
