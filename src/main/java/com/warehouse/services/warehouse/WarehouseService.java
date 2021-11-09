package com.warehouse.services.warehouse;

import com.fasterxml.jackson.databind.JsonNode;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> fetchAllCategories();

    Warehouse fetchCategoryById(Integer warehouse_id) throws EtBadRequestException;

    Warehouse addCategory(String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException;

    void updateCategory( Integer warehouse_id, String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException;

    void removeCustomer(Integer warehouse_id) throws EtResourceNotFoundException;
}
