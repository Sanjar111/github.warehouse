package com.warehouse.services.bin;

import com.warehouse.dao.bin.BinRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.bin.Bin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BinServiceImpl  implements BinService {

    @Autowired
    BinRepository binRepository;

    @Override
    public List<Bin> fetchAllCategories() {
        return binRepository.findAll();
    }

    @Override
    public Bin fetchCategoryById(Integer bin_id) throws EtBadRequestException {
        return binRepository.findById(bin_id);
    }

    @Override
    public Bin addCategory(Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException {
        int bin_id = binRepository.create(bin_number, total_size, busy_size,free_size, level_id, status);
        return binRepository.findById(bin_id);
    }

    @Override
    public void updateCategory(Integer bin_id, Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException {
        binRepository.update(bin_id, bin_number, total_size, busy_size, free_size, level_id, status);
    }
    @Override
    public void removeCustomerById(Integer bin_id) throws EtResourceNotFoundException {
        this.fetchCategoryById(bin_id);
    }
}

