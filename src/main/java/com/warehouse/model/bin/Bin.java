package com.warehouse.model.bin;

import com.warehouse.model.Level;
import io.swagger.v3.oas.annotations.media.Schema;

public class Bin {
    private Integer bin_id;
    private Integer bin_number;
    private Integer total_size;
    private Integer busy_size;
    private Integer free_size;
    private Integer level_id;
    private Integer status;
    private String created_time;
    private Level Bin;

    public Bin() {
    }

    public Bin(Integer bin_id, Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status, String created_time)  {
        this.bin_id = bin_id;
        this.bin_number = bin_number;
        this.total_size = total_size;
        this.busy_size = busy_size;
        this.free_size = free_size;
        this.level_id = level_id;
        this.status = status;
        this.created_time = created_time;
    }

    @Schema(hidden = true)
    public Integer getBin_id() {
        return bin_id;
    }
    @Schema(hidden = true)
    public void setBin_id(Integer bin_id) {
        this.bin_id = bin_id;
    }

    public Integer getBin_number() {
        return bin_number;
    }

    public void setBin_number(Integer bin_number) {
        this.bin_number = bin_number;
    }

    public Integer getTotal_size() {
        return total_size;
    }

    public void setTotal_size(Integer total_size) {
        this.total_size = total_size;
    }

    public Integer getBusy_size() {
        return busy_size;
    }

    public void setBusy_size(Integer busy_size) {
        this.busy_size = busy_size;
    }

    public Integer getFree_size() {
        return free_size;
    }

    public void setFree_size(Integer free_size) {
        this.free_size = free_size;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
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