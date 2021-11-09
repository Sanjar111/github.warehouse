package com.warehouse.controller;

import com.warehouse.model.Level;
import com.warehouse.services.level.LevelService;
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
@RequestMapping("api/level")
public class LevelController {

    @Autowired
    LevelService levelService;

    @GetMapping("")
    public ResponseEntity<List<Level>> getAllCategories(HttpServletRequest request){
        List<Level> categories = levelService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{block_id}")
    public ResponseEntity<Level> getCategoryById(HttpServletRequest request,
                                                 @PathVariable("level_id") Integer level_id){
        Level level = levelService.fetchCategoryById(level_id);
        return new ResponseEntity<>(level,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Level> addCategory(HttpServletRequest request,
                                             @RequestBody @Valid Level levelMap){
        Integer level_number = (Integer) levelMap.getLevel_number();
        Integer row_id = (Integer) levelMap.getRow_id();
        Integer[] subcategory_id_list = (Integer[]) levelMap.getSubcategory_id_list();
        Integer status = (Integer) levelMap.getStatus();
        Level category = levelService.addCategory(level_number,row_id,subcategory_id_list,status);
        return new ResponseEntity<>(category,HttpStatus.CREATED);

    }
    @PatchMapping("/{level_id}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable("level_id")Integer level_id, @RequestBody @Valid Level levelMap){
        Integer level_number = (Integer) levelMap.getLevel_number();
        Integer row_id = (Integer) levelMap.getRow_id();
        Integer[] subcategory_id_list = (Integer[]) levelMap.getSubcategory_id_list();
        Integer status = (Integer) levelMap.getStatus();
        levelService.updateCategory(level_id,level_number,row_id,subcategory_id_list,status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
