package com.warehouse.services.block;

import com.warehouse.dao.block.BlockRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Override
    public List<Block> fetchAllCategories() {
        return blockRepository.findAll();
    }

    @Override
    public Block fetchCategoryById(Integer block_id) throws EtBadRequestException {
        return blockRepository.findById(block_id);
    }

    @Override
    public Block addCategory(String block_name, Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        int block_id = blockRepository.create(block_name,warehouse_id,subcategory_id_list,status);
        return blockRepository.findById(block_id);
    }

    @Override
    public void updateCategory(Integer block_id, String block_name, Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        blockRepository.update(block_id,block_name,warehouse_id,subcategory_id_list,status);
    }

    @Override
    public void removeCustomerById(Integer block_id) throws EtResourceNotFoundException {
     this.fetchCategoryById(block_id);
    }
}
