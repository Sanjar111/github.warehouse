package com.warehouse.model;

public class Subcategory_bin {
    private Integer subcategory_id;
    private Integer bin_id;


    public Subcategory_bin(Integer subcategory_id, Integer bin_id){
        this.subcategory_id = subcategory_id;
        this.bin_id = bin_id;
    }

    public Integer getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(Integer subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public Integer getBin_id() {
        return bin_id;
    }

    public void setBin_id(Integer bin_id) {
        this.bin_id = bin_id;
    }
}
