package com.warehouse.controller;

import com.warehouse.model.Section;
import com.warehouse.services.section.SectionService;
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
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping("")
    public ResponseEntity<List<Section>> getAllCategories(HttpServletRequest request) {
        List<Section> categories = sectionService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @GetMapping("/{section_id}")
    public ResponseEntity<Section> getCategoryById(HttpServletRequest request,
                                                   @PathVariable("section_id") Integer section_id) {
        Section section = sectionService.fetchCategoryById(section_id);
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Section> addCategory(HttpServletRequest request,
                                                @RequestBody @Valid Section sectionMap) {
        Integer row_size = (Integer) sectionMap.getRow_size();
        Integer bin_id = (Integer) sectionMap.getBin_id();
        Integer[] subcategory_id_list = (Integer[]) sectionMap.getSubcategory_id_list();
        Integer size = (Integer) sectionMap.getSize();
        Integer product_id = (Integer) sectionMap.getProduct_id();
        Section category = sectionService.addCategory(row_size, bin_id, subcategory_id_list, size, product_id);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    @PatchMapping("/{section_id}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("section_id") Integer section_id, @RequestBody @Valid Section sectionMap) {
        Integer row_size = (Integer) sectionMap.getRow_size();
        Integer bin_id = (Integer) sectionMap.getBin_id();
        Integer[] subcategory_id_list = (Integer[]) sectionMap.getSubcategory_id_list();
        Integer size = (Integer) sectionMap.getSize();
        Integer product_id = (Integer) sectionMap.getProduct_id();
        sectionService.updateCategory(section_id, row_size, bin_id, subcategory_id_list, size, product_id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
