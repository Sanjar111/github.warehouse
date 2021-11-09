package com.warehouse.services.row;

import com.warehouse.dao.row.RowRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Row;
import com.warehouse.services.row.RowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RowServiceImpl  implements RowService {

    @Autowired
    RowRepository rowRepository;

    @Override
    public List<Row> fetchAllCategories() {
      return rowRepository.findAll();
    }

    @Override
    public Row fetchCategoryById(Integer row_id) throws EtBadRequestException {
        return rowRepository.findById(row_id);
    }

    @Override
    public Row addCategory(Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        int row_id = rowRepository.create(row_number,total_level,block_id,subcategory_id_list,status);
        return rowRepository.findById(row_id);
    }


    @Override
    public void updateCategory(Integer row_id, Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        rowRepository.update(row_id,row_number,total_level,block_id,subcategory_id_list,status);
    }

    @Override
    public void removeCustomerById(Integer row_id) throws EtResourceNotFoundException {
     this.fetchCategoryById(row_id);
    }
}
