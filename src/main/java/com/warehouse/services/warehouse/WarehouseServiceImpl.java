package com.warehouse.services.warehouse;

import com.fasterxml.jackson.databind.JsonNode;
import com.warehouse.dao.warehouse.WarehouseRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> fetchAllCategories() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse fetchCategoryById(Integer warehouse_id) throws EtBadRequestException {
        return warehouseRepository.findById(warehouse_id);
    }

    @Override
    public Warehouse addCategory(String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException {
        int warehouse_id = warehouseRepository.create(warehouse_name,subcategory_id_list,address,location,phone,status);
        return warehouseRepository.findById(warehouse_id);
    }

    @Override
    public void updateCategory(Integer warehouse_id, String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException {
        warehouseRepository.update(warehouse_id,warehouse_name,subcategory_id_list,address,location,phone,status);
    }

    @Override
    public void removeCustomer(Integer warehouse_id) throws EtResourceNotFoundException {
       this.fetchCategoryById(warehouse_id);
    }
}
