package com.warehouse.controller;


import com.warehouse.model.bin.Bin;
import com.warehouse.services.bin.BinService;
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
@RequestMapping ("/api/bin")
public class BinController {

    @Autowired
    BinService binService;

    @GetMapping("")
    public ResponseEntity<List<Bin>> getAllCategories(HttpServletRequest request){
       List<Bin> categories = binService.fetchAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{bin_id}")
    public ResponseEntity<Bin> getCategoryById(HttpServletRequest request,
                                               @PathVariable("bin_id") Integer bin_id){
        Bin bin = binService.fetchCategoryById(bin_id);
        return new ResponseEntity<>(bin,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Bin> addCategory(HttpServletRequest request,
                                           @RequestBody @Valid Bin binMap){
         Integer bin_number = (Integer) binMap.getBin_number();
         Integer total_size = (Integer) binMap.getTotal_size();
         Integer busy_size = (Integer) binMap.getBusy_size();
         Integer free_size = (Integer) binMap.getFree_size();
         Integer level_id = (Integer) binMap.getLevel_id();
         Integer status = (Integer) binMap.getStatus();
         Bin category = binService.addCategory(bin_number,total_size,busy_size,free_size,level_id,status);
         return new ResponseEntity<>(category,HttpStatus.CREATED);
        }

    @PatchMapping("/{bin_id}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable("bin_id")Integer bin_id, @RequestBody @Valid Bin binMap) {
        Integer bin_number = (Integer) binMap.getBin_number();
        Integer total_size = (Integer) binMap.getTotal_size();
        Integer busy_size = (Integer) binMap.getBusy_size();
        Integer free_size = (Integer) binMap.getFree_size();
        Integer level_id = (Integer) binMap.getLevel_id();
        Integer status = (Integer) binMap.getStatus();
        binService.updateCategory(bin_id,bin_number,total_size,busy_size,free_size,level_id,status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



    }



