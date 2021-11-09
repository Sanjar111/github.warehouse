package com.warehouse.services.subcategory_bin;



import com.warehouse.dao.subcategory_bin.Subcategory_binRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Subcategory_bin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class Subcategory_binServiceImpl implements Subcategory_binService {

    @Autowired
    Subcategory_binRepository subcategory_binRepository;

    @Override
    public List<Subcategory_bin> fetchAllCategories() {
        return subcategory_binRepository.findAll();
    }

    @Override
    public Subcategory_bin fetchCategoryById(Integer subcategory_id) throws EtBadRequestException {
        return subcategory_binRepository.findById(subcategory_id);
    }

    @Override
    public Subcategory_bin addCategory(Integer bin_id) throws EtBadRequestException {
        int subcategory = subcategory_binRepository.create(bin_id);
        return subcategory_binRepository.findById(bin_id);
    }

    @Override
    public void updateCategory(Integer subcategory_id, Integer bin_id) throws EtBadRequestException {
        subcategory_binRepository.update(subcategory_id);
    }

    @Override
    public void removeCustomerById(Integer subcategory_id) throws EtResourceNotFoundException {
        this.fetchCategoryById(subcategory_id);
    }
}