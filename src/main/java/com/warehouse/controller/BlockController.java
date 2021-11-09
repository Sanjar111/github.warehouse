package com.warehouse.controller;

import com.warehouse.model.Block;
import com.warehouse.services.block.BlockService;
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
@RequestMapping("api/block")
public class BlockController {

    @Autowired
    BlockService blockService;

    @GetMapping("")
    public ResponseEntity<List<Block>> getAllCategories(HttpServletRequest request){
        List<Block> categories = blockService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{block_id}")
    public ResponseEntity<Block> getCategoryById(HttpServletRequest request,
                                                       @PathVariable("block_id") Integer block_id){
       Block block = blockService.fetchCategoryById(block_id);
       return new ResponseEntity<>(block,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Block> addCategory(HttpServletRequest request,
                                             @RequestBody @Valid Block blockMap){
        String block_name = (String) blockMap.getBlock_name();
        Integer warehouse_id = (Integer) blockMap.getWarehouse_id();
        Integer[] subcategory_id_list = (Integer[]) blockMap.getSubcategory_id_list();
        Integer status = (Integer) blockMap.getStatus();
        Block category = blockService.addCategory(block_name,warehouse_id,subcategory_id_list,status);
        return new ResponseEntity<>(category,HttpStatus.CREATED);

    }
    @PatchMapping("/{block_id}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable("block_id")Integer block_id, @RequestBody @Valid Block blockMap){
        String block_name = (String) blockMap.getBlock_name();
        Integer warehouse_id = (Integer) blockMap.getWarehouse_id();
        Integer[] subcategory_id_list = (Integer[]) blockMap.getSubcategory_id_list();
        Integer status = (Integer) blockMap.getStatus();
        blockService.updateCategory(block_id,block_name,warehouse_id,subcategory_id_list,status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);


       }

    }


