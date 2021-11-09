package com.warehouse.dao.row;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RowRepositoryImpl implements RowRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM ROW";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM ROW WHERE ROW_ID = ?";
    private static final String SQL_CREATED = "INSERT INTO ROW(ROW_ID,ROW_NUMBER, TOTAL_LEVEL, BLOCK_ID, SUBCATEGORY_ID_LIST, STATUS) VALUES(NEXTVAL('ROW_SEQ'),?,?,?,?::JSON,?)";
    private static final String SQL_UPDATE = "UPDATE ROW SET ROW_NUMBER = ?, TOTAL_LEVEL = ?, BLOCK_ID = ?, SUBCATEGORY_ID_LIST = ?::JSON, STATUS = ? " + "WHERE ROW_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Row> findAll() throws EtBadRequestException {
        return jdbcTemplate.query(SQL_FIND_ALL, new RowRowMapper());
    }

    @Override
    public Row findById(Integer row_id) throws EtBadRequestException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new RowRowMapper(), new Object[]{row_id});
        } catch (Exception e) {
            throw new EtResourceNotFoundException("row not found");
        }
    }

    @Override
    public Integer create(Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i =0; i < subcategory_id_list.length; i++) {
                listOfString.add(subcategory_id_list[i]);
            }

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, row_number);
                ps.setInt(2, total_level);
                ps.setInt(3, block_id);
                ps.setObject(4,  listOfString.toString());
                ps.setInt(5, status);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ROW_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer row_id, Integer row_number, Integer total_level, Integer block_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i =0; i < subcategory_id_list.length; i++) {
                listOfString.add(subcategory_id_list[i]);
            }

            jdbcTemplate.update(SQL_UPDATE, new Object[]{row_number, total_level, block_id,  listOfString.toString(), status, row_id});
            throw new EtBadRequestException("Invalid request update");
        } catch (Exception e) {

        }
    }


}

