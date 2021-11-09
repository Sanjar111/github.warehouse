package com.warehouse.dao.section;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.warehouse.model.Section;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionRowMapper implements RowMapper<Section> {

    @Override
    public Section mapRow(ResultSet rs, int rowNum) throws SQLException {

        JsonArray subcategory_id_list = convertToEntityAttribute(rs.getObject("SUBCATEGORY_ID_LIST"));
        Integer[] listTags = new Integer[subcategory_id_list.size()];
        for (int i = 0; i < subcategory_id_list.size(); i++){
            listTags[i] = subcategory_id_list.get(i).getAsInt();
        }

        Section section = new Section();
        section.setSection_id(rs.getInt("SECTION_ID"));
        section.setRow_size(rs.getInt("ROW_SIZE"));
        section.setBin_id(rs.getInt("BIN_ID"));
        section.setSubcategory_id_list(listTags);
        section.setSize(rs.getInt("SIZE"));
        section.setProduct_id(rs.getInt("PRODUCT_ID"));
        section.setBusy_time(rs.getString("BUSY_TIME"));
        section.setFree_time(rs.getString("FREE_TIME"));
        return section;
    }

    public JsonArray convertToEntityAttribute(Object dbData) {
        if(dbData instanceof PGobject) {
            JsonParser parser = new JsonParser();
            return parser.parse(((PGobject) dbData).getValue()).getAsJsonArray();
        }
        return null;
    }
}
