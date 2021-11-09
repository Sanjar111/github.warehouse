package com.warehouse.model;


import io.swagger.v3.oas.annotations.media.Schema;

public class Row {
    private Integer row_id;
    private Integer row_number;
    private Integer total_level;
    private Integer block_id;
    private Integer[] subcategory_id_list;
    private Integer status;
    private String created_time;
    private Block Row;

    public Row() {

    }

    public Row(Integer row_id, Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status, String created_time) {
       this.row_id = row_id;
       this.row_number =row_number;
       this.total_level = total_level;
       this.block_id = block_id;
       this.subcategory_id_list = subcategory_id_list;
       this.status = status;
       this.created_time = created_time;
    }
    @Schema(hidden = true)
    public Integer getRow_id() {
        return row_id;
    }
    @Schema(hidden = true)
    public void setRow_id(Integer row_id) {
        this.row_id = row_id;
    }

    public Integer getRow_number() {
        return row_number;
    }

    public void setRow_number(Integer row_number) {
        this.row_number = row_number;
    }

    public Integer getTotal_level() {
        return total_level;
    }

    public void setTotal_level(Integer total_level) {
        this.total_level = total_level;
    }

    public Integer getBlock_id() {
        return block_id;
    }

    public void setBlock_id(Integer block_id) {
        this.block_id = block_id;
    }

    public Integer[] getSubcategory_id_list() {
        return subcategory_id_list;
    }

    public void setSubcategory_id_list(Integer[] subcategory_id_list) {
        this.subcategory_id_list = subcategory_id_list;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Schema(hidden = true)
    public String getCreated_time() {
        return created_time;
    }
    @Schema(hidden = true)
    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
