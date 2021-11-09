package com.warehouse.dao.subcategory_bin;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Subcategory_bin;

import java.util.List;

public interface Subcategory_binRepository {

    List<Subcategory_bin> findAll() throws EtResourceNotFoundException;

    Subcategory_bin findById(Integer subcategory_id) throws  EtResourceNotFoundException;

    Integer create(Integer bin_id) throws EtBadRequestException;

    void update(Integer subcategory_id, Integer bin_id)throws  EtBadRequestException;

    void update(Integer bin_id);
}
