package com.warehouse.controller;

import com.warehouse.model.Block;
import com.warehouse.model.Row;
import com.warehouse.services.row.RowService;
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
@RequestMapping ("/api/row")
public class RowController {

    @Autowired
    RowService rowService;

    @GetMapping("")
    public ResponseEntity<List<Row>> getAllCategories(HttpServletRequest request){
       List<Row> categories = rowService.fetchAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{row_id}")
    public ResponseEntity<Row> getCategoryById(HttpServletRequest request,
                                               @PathVariable("row_id") Integer row_id){
        Row row = rowService.fetchCategoryById(row_id);
        return new ResponseEntity<>(row,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Row> addCategory(HttpServletRequest request,
                                           @RequestBody @Valid Row rowMap){
         Integer row_number = (Integer) rowMap.getRow_number();
         Integer total_level = (Integer) rowMap.getTotal_level();
         Integer block_id = (Integer) rowMap.getBlock_id();
         Integer[] subcategory_id_list = (Integer[]) rowMap.getSubcategory_id_list();
         Integer status = (Integer) rowMap.getStatus();
         Row category = rowService.addCategory(row_number,total_level,block_id,subcategory_id_list,status);
         return new ResponseEntity<>(category,HttpStatus.CREATED);

        }
    @PatchMapping("/{row_id}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable("row_id")Integer row_id, @RequestBody @Valid Row rowMap){
        Integer row_number = (Integer) rowMap.getRow_number();
        Integer total_level = (Integer) rowMap.getTotal_level();
        Integer block_id = (Integer) rowMap.getBlock_id();
        Integer[] subcategory_id_list = (Integer[]) rowMap.getSubcategory_id_list();
        Integer status = (Integer) rowMap.getStatus();
        rowService.updateCategory(row_id,row_number,total_level,block_id,subcategory_id_list,status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
