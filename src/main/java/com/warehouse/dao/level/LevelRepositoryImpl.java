package com.warehouse.dao.level;



import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Level;
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
public class LevelRepositoryImpl implements LevelRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM LEVEL";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM LEVEL WHERE LEVEL_ID = ?" ;
    private static final String SQL_CREATED = "INSERT INTO LEVEL(LEVEL_ID, LEVEL_NUMBER, ROW_ID, SUBCATEGORY_ID_LIST, STATUS) VALUES(NEXTVAL('LEVEL_SEQ'), ?, ?, ?::JSON, ?)";
    private static final String SQL_UPDATE = "UPDATE LEVEL SET LEVEL_NUMBER = ?, ROW_ID = ?, SUBCATEGORY_ID_LIST = ?::JSON, STATUS = ? WHERE LEVEL_ID = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Level> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new LevelRowMapper());
    }

    @Override
    public Level findById(Integer level_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new LevelRowMapper(), new Object[]{level_id});
        } catch (Exception e){
            throw new EtResourceNotFoundException("level not found");
        }
    }

    @Override
    public Integer create(Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i = 0; i < subcategory_id_list.length; i++) {
                listOfString.add(subcategory_id_list[i]);


            }
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, level_number);
                ps.setInt(2, row_id);
                ps.setObject(3, listOfString.toString());
                ps.setInt(4, status);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("LEVEL_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }
    @Override
    public void update(Integer level_id,Integer level_number, Integer row_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException{
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i =0; i < subcategory_id_list.length; i++){
                listOfString.add(subcategory_id_list[i]);
            }
            jdbcTemplate.update(SQL_UPDATE, new Object[]{level_number,row_id,listOfString.toString(),status, level_id});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request update");
        }
    }

}