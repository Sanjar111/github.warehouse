package com.warehouse.dao.row;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Row;

import java.util.List;

public interface RowRepository {
    List<Row> findAll() throws EtResourceNotFoundException;
    Row findById(Integer row_id) throws EtResourceNotFoundException;
    Integer create(Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;
    void update( Integer row_id, Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;
}
