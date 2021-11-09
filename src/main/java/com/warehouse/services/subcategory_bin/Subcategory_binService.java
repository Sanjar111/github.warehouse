package com.warehouse.services.subcategory_bin;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Subcategory_bin;

import java.util.List;


public interface Subcategory_binService {


    List<Subcategory_bin> fetchAllCategories();

    Subcategory_bin fetchCategoryById(Integer subcategory_id) throws EtBadRequestException;

    Subcategory_bin addCategory(Integer bin_id) throws EtBadRequestException;

    void updateCategory(Integer subcategory_id, Integer bin_id) throws EtBadRequestException;

    void removeCustomerById(Integer subcategory_id) throws EtResourceNotFoundException;

}
