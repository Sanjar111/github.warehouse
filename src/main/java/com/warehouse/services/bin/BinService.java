package com.warehouse.services.bin;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.bin.Bin;

import java.util.List;

public interface BinService {

    List<Bin> fetchAllCategories();

    Bin fetchCategoryById(Integer bin_id) throws EtBadRequestException;

    Bin addCategory(Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws  EtBadRequestException;

    void updateCategory(Integer bin_id, Integer bin_number, Integer total_size,Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException;

    void removeCustomerById(Integer bin_id) throws EtResourceNotFoundException;
}
