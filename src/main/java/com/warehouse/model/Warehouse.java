package com.warehouse.model;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;

public class Warehouse {
    private Integer warehouse_id;
    private String warehouse_name;
    private Integer[] subcategory_id_list;
    private String address;
    private JsonNode[] location;
    private String phone;
    private Integer status;
    private String created_time;

    public Warehouse() {
    }

    public Warehouse(Integer warehouse_id, String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location,Integer status, String phone, String created_time){
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.subcategory_id_list = subcategory_id_list;
        this.address = address;
        this.location = location;
        this.status = status;
        this.phone = phone;
        this.created_time = created_time;
    }


    @Schema(hidden = true)
    public Integer getWarehouse_id() {
        return warehouse_id;
    }

    @Schema(hidden = true)
    public void setWarehouse_id(Integer warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public Integer[] getSubcategory_id_list() {
        return subcategory_id_list;
    }

    public void setSubcategory_id_list(Integer[] subcategory_id_list) {
        this.subcategory_id_list = subcategory_id_list;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JsonNode[] getLocation() {
        return location;
    }

    public void setLocation(JsonNode[] location) {
        this.location = location;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
