package com.warehouse.dao.block;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.warehouse.model.Block;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockRowMapper implements RowMapper<Block> {

    @Override
    public Block mapRow(ResultSet rs, int rowNum) throws SQLException {

        JsonArray subcategory_id_list = convertToEntityAttribute(rs.getObject("SUBCATEGORY_ID_LIST"));
        Integer[] listTags = new Integer[subcategory_id_list.size()];
        for (int i = 0; i < subcategory_id_list.size(); i++){
            listTags[i] = subcategory_id_list.get(i).getAsInt();
        }

        Block block = new Block();
        block.setBlock_id(rs.getInt("BLOCK_ID"));
        block.setBlock_name(rs.getString("BLOCK_NAME"));
        block.setWarehouse_id(rs.getInt("WAREHOUSE_ID"));
        block.setSubcategory_id_list(listTags);
        block.setStatus(rs.getInt("STATUS"));
        block.setCreated_time(rs.getString("CREATED_TIME"));
        return block;
    }

    public JsonArray convertToEntityAttribute(Object dbData) {
        if(dbData instanceof PGobject) {
            JsonParser parser = new JsonParser();
            return parser.parse(((PGobject) dbData).getValue()).getAsJsonArray();
        }
        return null;
    }
}