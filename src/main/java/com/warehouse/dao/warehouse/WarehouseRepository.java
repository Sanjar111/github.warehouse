package com.warehouse.dao.warehouse;

import com.fasterxml.jackson.databind.JsonNode;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Warehouse;

import java.util.List;

public interface WarehouseRepository {

    List<Warehouse> findAll() throws EtResourceNotFoundException;

    Warehouse findById(Integer warehouse_id) throws EtResourceNotFoundException;

    Integer create(String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException;

    void update(Integer warehouse_id, String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException;
}

