package com.warehouse.dao.section;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SectionRepositoryImpl implements SectionRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM SECTION";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM SECTION WHERE SECTION_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO SECTION (SECTION_ID, ROW_SIZE, BIN_ID, SUBCATEGORY_ID_LIST, SIZE, PRODUCT_ID) VALUES(NEXTVAL('SECTION_SEQ'),?, ?, ?::JSON, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE SECTION SET ROW_SIZE = ?, BIN_ID =?, SUBCATEGORY_ID_LIST = ?::JSON, SIZE = ?, PRODUCT_ID = ?"
            + "WHERE SECTION_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Section> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new SectionRowMapper());
    }

    @Override
    public Section findById(Integer section_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new SectionRowMapper(), new Object[]{section_id});
        } catch (Exception e) {
            throw new EtResourceNotFoundException("section not found");
        }
    }
    @Override
    public Integer create( Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id)
            throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i = 0; i < subcategory_id_list.length; i++) {
                listOfString.add(subcategory_id_list[i]);
            }
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,row_size);
                ps.setInt(2,bin_id);
                ps.setObject(3, listOfString.toString());
                ps.setInt(4, size);
                ps.setInt(5, product_id);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("SECTION_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer section_id, Integer row_size, Integer bin_id, Integer[] subcategory_id_list, Integer size, Integer product_id)
            throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i = 0; i < subcategory_id_list.length; i++) {
                listOfString.add(subcategory_id_list[i]);
            }

            jdbcTemplate.update(SQL_UPDATE, new Object[] {row_size, bin_id, listOfString.toString(), size, product_id, section_id });
            throw new EtBadRequestException("Invalid request update");
        } catch (Exception e) {
        }
    }

}
