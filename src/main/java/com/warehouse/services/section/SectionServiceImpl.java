package com.warehouse.services.section;

import com.warehouse.dao.section.SectionRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<Section> fetchAllCategories() {
        return sectionRepository.findAll();
    }

    @Override
    public Section fetchCategoryById(Integer section_id) throws EtResourceNotFoundException {
        return sectionRepository.findById(section_id);
    }

    @Override
    public Section addCategory(Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id) throws EtBadRequestException {
        Integer section_id = sectionRepository.create(row_size, bin_id,subcategory_id_list, size, product_id);
        return sectionRepository.findById(section_id);
    }

    @Override
    public void updateCategory(Integer section_id,Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id) throws EtBadRequestException {
      sectionRepository.update(section_id,row_size,bin_id,subcategory_id_list,size,product_id);
    }

    @Override
    public void removeCustomerById(Integer section_id) throws EtResourceNotFoundException {
      this.fetchCategoryById(section_id);
    }
}
