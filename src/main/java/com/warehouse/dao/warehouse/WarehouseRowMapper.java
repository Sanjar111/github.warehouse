package com.warehouse.dao.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.warehouse.model.Warehouse;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarehouseRowMapper  implements RowMapper<Warehouse> {

    @Override
    public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {

        JsonArray arr = convertToEntityAttribute(rs.getObject("LOCATION"));
        JsonNode[] list = new JsonNode[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            ObjectMapper mapper = new ObjectMapper();
            JsonObject node = arr.get(i).getAsJsonObject();
            try {
                JsonNode actualObj = mapper.readTree(String.valueOf(node));
                list[i] = actualObj;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        JsonArray subcategory_id_list = convertToEntityAttribute(rs.getObject("SUBCATEGORY_ID_LIST"));
        Integer[] listTags = new Integer[subcategory_id_list.size()];
        for (int i = 0; i < subcategory_id_list.size(); i++){
            listTags[i] = subcategory_id_list.get(i).getAsInt();
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouse_id(rs.getInt("WAREHOUSE_ID"));
        warehouse.setWarehouse_name(rs.getString("WAREHOUSE_NAME"));
        warehouse.setSubcategory_id_list(listTags);
        warehouse.setAddress(rs.getString("ADDRESS"));
        warehouse.setLocation(list);
        warehouse.setPhone(rs.getString("PHONE"));
        warehouse.setStatus(rs.getInt("STATUS"));
        warehouse.setCreated_time(rs.getString("CREATED_TIME"));
        return warehouse;
    }

    public JsonArray convertToEntityAttribute(Object dbData) {
        if(dbData instanceof PGobject) {
            JsonParser parser = new JsonParser();
            return parser.parse(((PGobject) dbData).getValue()).getAsJsonArray();
        }
        return null;
    }
}

