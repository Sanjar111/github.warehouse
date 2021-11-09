package com.warehouse.dao.block;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Block;

import java.util.List;

public interface BlockRepository {

    List<Block> findAll() throws EtResourceNotFoundException;

    Block findById(Integer block_id) throws EtResourceNotFoundException;

    Integer create(String block_name,Integer warehouse_id, Integer[] subcategory_id_list, Integer status)throws EtBadRequestException;

    void update(Integer block_id, String block_name,Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException;

}
