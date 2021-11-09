package com.warehouse.dao.row;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.warehouse.model.Row;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowRowMapper implements RowMapper<Row> {

    @Override
    public Row mapRow(ResultSet rs, int rowNum) throws SQLException {



        JsonArray subcategory_id_list = convertToEntityAttribute(rs.getObject("SUBCATEGORY_ID_LIST"));
        Integer[] listTags = new Integer[subcategory_id_list.size()];
        for (int i = 0; i < subcategory_id_list.size(); i++){
            listTags[i] = subcategory_id_list.get(i).getAsInt();
        }

        Row row = new Row();
        row.setRow_id(rs.getInt("ROW_ID"));
        row.setRow_number(rs.getInt("ROW_NUMBER"));
        row.setTotal_level(rs.getInt("TOTAL_LEVEL"));
        row.setBlock_id(rs.getInt("BLOCK_ID"));
        row.setSubcategory_id_list(listTags);
        row.setStatus(rs.getInt("STATUS"));
        row.setCreated_time(rs.getString("CREATED_TIME"));
        return row;
    }

    public JsonArray convertToEntityAttribute(Object dbData) {
        if(dbData instanceof PGobject) {
            JsonParser parser = new JsonParser();
            return parser.parse(((PGobject) dbData).getValue()).getAsJsonArray();
        }
        return null;
    }
}


