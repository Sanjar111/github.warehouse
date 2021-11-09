package com.warehouse.model;


import io.swagger.v3.oas.annotations.media.Schema;

public class Block {
    private Integer block_id;
    private String block_name;
    private Integer warehouse_id;
    private Integer[] subcategory_id_list;
    private Integer status;
    private String created_time;
    private Warehouse Block;

    public Block() {
    }

    public Block(Integer block_id, String block_name, Integer warehouse_id, Integer[] subcategory_id_list, Integer status, String created_time){
        this.block_id = block_id;
        this.block_name = block_name;
        this.warehouse_id = warehouse_id;
        this.subcategory_id_list = subcategory_id_list;
        this.status = status;
        this.created_time = created_time;
    }


    @Schema(hidden = true)
    public Integer getBlock_id() {
        return block_id;
    }
    @Schema(hidden = true)
    public void setBlock_id(Integer block_id) {
        this.block_id = block_id;
    }

    public String getBlock_name() {
        return block_name;
    }

    public Integer getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(Integer warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
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