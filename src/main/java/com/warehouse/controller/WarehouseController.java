package com.warehouse.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.warehouse.model.Warehouse;
import com.warehouse.services.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("")
    public ResponseEntity<List<Warehouse>> getAllCategories(HttpServletRequest request){
       List<Warehouse> categories = warehouseService.fetchAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{warehouse_id}")
    public ResponseEntity<Warehouse> getCategoryById(HttpServletRequest request,
                                                     @PathVariable("warehouse_id") Integer warehouse_id ){
       Warehouse warehouse = warehouseService.fetchCategoryById(warehouse_id);
       return new ResponseEntity<>(warehouse,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Warehouse> addCategory(HttpServletRequest request,
                                                 @RequestBody @Valid Warehouse warehouseMap){
        String warehouse_name = (String) warehouseMap.getWarehouse_name();
        Integer[] subcategory_id_list = (Integer[]) warehouseMap.getSubcategory_id_list();
        String address = (String) warehouseMap.getAddress();
        JsonNode[] location = (JsonNode[]) warehouseMap.getLocation();
        String phone = (String) warehouseMap.getPhone();
        Integer status = (Integer) warehouseMap.getStatus();
        Warehouse category = warehouseService.addCategory(warehouse_name,subcategory_id_list,address,location,phone,status);
        return new ResponseEntity<>(category,HttpStatus.CREATED);

        }
    @PatchMapping("/{warehouse_id}")
    public ResponseEntity<Map<String,Boolean>>updateCategory(HttpServletRequest request,
                                                             @PathVariable("warehouse_id") Integer warehouse_id, @RequestBody @Valid Warehouse warehouseMap){
        String warehouse_name = (String) warehouseMap.getWarehouse_name();
        Integer[] subcategory_id_list = (Integer[]) warehouseMap.getSubcategory_id_list();
        String address = (String) warehouseMap.getAddress();
        JsonNode[] location = (JsonNode[]) warehouseMap.getLocation();
        String phone = (String) warehouseMap.getPhone();
        Integer status = (Integer) warehouseMap.getStatus();
        warehouseService.updateCategory(warehouse_id,warehouse_name,subcategory_id_list,address,location,phone,status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

}
