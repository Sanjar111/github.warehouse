package com.warehouse.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Section {
    private  Integer section_id;
    private Integer row_size;
    private Integer bin_id;
    private  Integer[] subcategory_id_list;
    private  Integer size;
    private  Integer product_id;
    private  String busy_time;
    private  String free_time;

    public Section() {
    }
    public Section(Integer section_id, Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id, String busy_time, String free_time){
        this.section_id = section_id;
        this.row_size = row_size;
        this.bin_id = bin_id;
        this.subcategory_id_list = subcategory_id_list;
        this.size = size;
        this.product_id = product_id;
        this.busy_time = busy_time;
        this.free_time = free_time;
    }
    @Schema(hidden = true)
    public Integer getSection_id() {
        return section_id;
    }
    @Schema(hidden = true)
    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Integer getRow_size() {
        return row_size;
    }

    public void setRow_size(Integer row_size) {
        this.row_size = row_size;
    }

    public Integer getBin_id() {
        return bin_id;
    }

    public void setBin_id(Integer bin_id) {
        this.bin_id = bin_id;
    }

    public Integer[] getSubcategory_id_list() {
        return subcategory_id_list;
    }

    public void setSubcategory_id_list(Integer[] subcategory_id_list) {
        this.subcategory_id_list = subcategory_id_list;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
    @Schema(hidden = true)
    public String getBusy_time() {
        return busy_time;
    }
    @Schema(hidden = true)
    public void setBusy_time(String busy_time) {
        this.busy_time = busy_time;
    }
    @Schema(hidden = true)
    public String getFree_time() {
        return free_time;
    }
    @Schema(hidden = true)
    public void setFree_time(String free_time) {
        this.free_time = free_time;
    }
}
