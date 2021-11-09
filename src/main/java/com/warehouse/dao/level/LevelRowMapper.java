package com.warehouse.dao.level;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.warehouse.model.Level;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelRowMapper implements RowMapper<Level> {

    @Override
    public Level mapRow(ResultSet rs, int rowNum) throws SQLException {

        JsonArray subcategory_id_list = convertToEntityAttribute(rs.getObject("SUBCATEGORY_ID_LIST"));
        Integer[] listTags = new Integer[subcategory_id_list.size()];
        for (int i = 0; i < subcategory_id_list.size(); i++){
            listTags[i] = subcategory_id_list.get(i).getAsInt();
        }

        Level level = new Level();
        level.setLevel_id(rs.getInt("LEVEL_ID"));
        level.setLevel_number(rs.getInt("LEVEL_NUMBER"));
        level.setRow_id(rs.getInt("ROW_ID"));
        level.setSubcategory_id_list(listTags);
        level.setStatus(rs.getInt("STATUS"));
        level.setCreated_time(rs.getString("CREATED_TIME"));
        return level;
    }

    public JsonArray convertToEntityAttribute(Object dbData) {
        if(dbData instanceof PGobject) {
            JsonParser parser = new JsonParser();
            return parser.parse(((PGobject) dbData).getValue()).getAsJsonArray();
        }
        return null;
    }
}
