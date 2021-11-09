package com.warehouse.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Level {
    private Integer level_id;
    private Integer level_number;
    private Integer row_id;
    private Integer[] subcategory_id_list;
    private Integer status;
    private String created_time;
    private Row Level;

    public Level() {
    }
    public Level(Integer level_id, Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status, String created_time){
        this.level_id = level_id;
        this.level_number = level_number;
        this.row_id = row_id;
        this.subcategory_id_list = subcategory_id_list;
        this.status = status;
        this.created_time = created_time;
    }
    @Schema(hidden = true)
    public Integer getLevel_id() {
        return level_id;
    }
    @Schema(hidden = true)
    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public Integer getLevel_number() {
        return level_number;
    }

    public void setLevel_number(Integer level_number) {
        this.level_number = level_number;
    }

    public Integer getRow_id() {
        return row_id;
    }

    public void setRow_id(Integer row_id) {
        this.row_id = row_id;
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
