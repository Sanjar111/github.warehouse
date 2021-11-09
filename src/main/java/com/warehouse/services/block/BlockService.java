package com.warehouse.services.block;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Block;

import java.util.List;

public interface BlockService {

    List<Block> fetchAllCategories();

    Block fetchCategoryById(Integer block_id) throws EtBadRequestException;

    Block addCategory(String block_name,Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

    void updateCategory(Integer block_id, String block_name,Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

    void removeCustomerById(Integer block_id) throws EtResourceNotFoundException;
}
