package com.warehouse.dao.section;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Section;

import java.util.List;

public interface SectionRepository {
    List<Section> findAll() throws EtResourceNotFoundException;
    Section findById(Integer section_id) throws EtResourceNotFoundException;
    Integer create(Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id) throws EtBadRequestException;
    void update(Integer section_id, Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id) throws EtBadRequestException;

}
