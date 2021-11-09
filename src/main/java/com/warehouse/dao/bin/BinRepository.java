package com.warehouse.dao.bin;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.bin.Bin;


import java.util.List;

public interface BinRepository {

    List<Bin> findAll()throws EtResourceNotFoundException;

    Bin findById(Integer bin_id) throws EtResourceNotFoundException;

    Integer create( Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException;

    void update(Integer bin_id, Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException;
}
