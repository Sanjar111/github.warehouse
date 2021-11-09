package com.warehouse.services.row;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Row;

import java.util.List;

public interface RowService {
    List<Row> fetchAllCategories();
    Row fetchCategoryById(Integer row_id) throws EtBadRequestException;
    Row addCategory(Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;
    void updateCategory( Integer row_id, Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;
    void removeCustomerById(Integer row_id) throws EtResourceNotFoundException;
}
