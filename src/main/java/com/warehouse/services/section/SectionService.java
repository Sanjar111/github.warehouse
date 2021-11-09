package com.warehouse.services.section;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Section;

import java.util.List;

public interface SectionService {

    List<Section> fetchAllCategories();

    Section fetchCategoryById(Integer section_id) throws EtResourceNotFoundException;

    Section addCategory(Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id)throws EtBadRequestException;

    void updateCategory(Integer section_id, Integer row_size, Integer bin_id, Integer[] subcategory_id_list,Integer size,Integer product_id)throws EtBadRequestException;

    void removeCustomerById(Integer section_id)throws EtResourceNotFoundException;


}
